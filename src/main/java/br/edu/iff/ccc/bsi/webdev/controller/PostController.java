package br.edu.iff.ccc.bsi.webdev.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.dto.PostDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.PostService;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/post")
@Tag(name = "Posts", description = "API para gerenciamento de posts")
public class PostController {
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private PostDTO postDTO;
	
	@Autowired
	private UserComumService userComumService;

	
    @GetMapping("/{id}")
    @Operation(summary = "Buscar um post por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    public EntityModel<Post> getPost(@PathVariable Long id) {
        Post post = postService.findById(id);
        return postDTO.toModel(post);
    }

    @PostMapping()
    @Operation(summary = "Criar um novo post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public EntityModel<Post> createPost( @Valid @RequestBody Post post) {
    	UserComum user = userComumService.findById(post.getId());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId inválido");
		}
		post.setAuthor(user);
        Post createdPost = postService.save(post);
        return postDTO.toModel(createdPost);
    }
    
    @GetMapping()
    @Operation(summary = "Listar todos os posts")
    public CollectionModel<EntityModel<Post>> getAllPosts() {
        List<EntityModel<Post>> posts = postService.findAll().stream()
                .map(postDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(posts, linkTo(methodOn(PostController.class).getAllPosts()).withSelfRel());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um post existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Post não encontrado")
    })
    public EntityModel<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post updatedPost = postService.update(id, postDetails);
        return postDTO.toModel(updatedPost);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um post por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Post deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Post não encontrado")
    }) 
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }
    
}
