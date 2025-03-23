package br.edu.iff.ccc.bsi.webdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.services.CommentService;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	
	// Endpoint para criar um novo usuário
    @PostMapping("/create")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Comment getComment(@PathVariable Long id) {
        return commentService.findById(id);
    }
    
    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.findAll();
    }
    
    @GetMapping("/search/{content}")
    public List<Comment> getCommentsByContent(@PathVariable String content) {
        return commentService.findByContent(content);
    }
    
    @PutMapping("/update/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody Comment commentDetails) {
        return commentService.update(id, commentDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
    }
    
}
