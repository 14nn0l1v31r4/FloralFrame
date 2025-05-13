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

import br.edu.iff.ccc.bsi.webdev.dto.CommentDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.CommentService;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/comment")
@Tag(name = "Comentários", description = "API para gerenciamento de comentários")
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
	private UserComumService userComumService;
	
	@Autowired
	private CommentDTO commentDTO;
	
    @PostMapping()
    @Operation(summary = "Criar um novo comentário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    public EntityModel<Comment> createComment(@RequestBody Comment comment) {
    	UserComum user = userComumService.findByUser(comment.getUserIdComment());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserId inválido");
		}
        Comment createdComment = commentService.save(comment);
        return commentDTO.toModel(createdComment);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar comentário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentário encontrado"),
        @ApiResponse(responseCode = "404", description = "Comentário não encontrado")
    })
    public EntityModel<Comment> getComment(@PathVariable Long id) {
        Comment comment = commentService.findById(id);
        return commentDTO.toModel(comment);
    }
    
    @GetMapping()
    @Operation(summary = "Listar todos os comentários")
    public CollectionModel<EntityModel<Comment>> getAllComments() {
        List<EntityModel<Comment>> comments = commentService.findAll().stream()
                .map(commentDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(comments, linkTo(methodOn(CommentController.class).getAllComments()).withSelfRel());
    }
    
    @GetMapping("/content/{content}")
    @Operation(summary = "Buscar comentários pelo conteúdo")
    public List<Comment> getCommentsByContent(@PathVariable String content) {
        return commentService.findByContent(content);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um comentário existente")
    public EntityModel<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        Comment updatedComment = commentService.update(id, commentDetails);
        return commentDTO.toModel(updatedComment);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um comentário por ID")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }
    
    
}
