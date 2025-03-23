package br.edu.iff.ccc.bsi.webdev.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import br.edu.iff.ccc.bsi.webdev.services.PostService;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
	
	@Autowired
    private PostService postService;
	

    // Endpoint para criar um novo post


    // Endpoint para buscar um post por ID
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.findById(id);
    }
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public Post createPost(@RequestParam String title, 
                           @RequestParam String body, 
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam CategoryPost category) {
        return postService.createPost(title, body, LocalDate.now(), CategoryPost.AQUATICA);
    }
    
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }
    
    @PutMapping("/update/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.update(id, postDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteById(id);
    }
    
}
