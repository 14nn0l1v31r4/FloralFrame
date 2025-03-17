package br.edu.iff.ccc.bsi.webdev.controllers;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.CategoryPostEntity;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.repository.CategoryPostRepository;
import br.edu.iff.ccc.bsi.webdev.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
    private PostService postService;
	
	@Autowired
	private CategoryPostRepository categoryPostRepository;

    // Endpoint para criar um novo post
    @PostMapping


    // Endpoint para buscar um post por ID
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.findById(id);
    }
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestParam String title, 
                           @RequestParam String body, 
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam Long categoryId) {
        // Buscar a categoria no banco
        CategoryPostEntity category = categoryPostRepository.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        return postService.createPost(1L, title, body, Date.valueOf(date), category);
    }
    
}
