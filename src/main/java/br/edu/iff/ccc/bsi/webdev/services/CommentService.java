package br.edu.iff.ccc.bsi.webdev.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.exception.CommentNotFoundException;
import br.edu.iff.ccc.bsi.webdev.repository.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepo;

    // Método para buscar um comentário por ID
    public Comment findById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    // Método para criar um novo comentário
    public Comment createComment(String content, boolean like, LocalDate createdAt , Post post, UserComum author) {
        Comment comment = new Comment(author, like, createdAt, content, post);
        comment.setContent(content);
        return commentRepo.save(comment);
    }

    // Método para salvar um comentário
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }
    
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }
    
    public List<Comment> findByContent(String content) {
        return commentRepo.findByContent(content);
    }
    
    public Comment update(Long id, Comment commentDetails) {
		if (!commentRepo.existsById(id)) {
			throw new CommentNotFoundException();
		}
        Comment comment = findById(id);
        comment.setContent(commentDetails.getContent());
        return commentRepo.save(comment);
    }
    
    public void deleteById(Long id) {
        if (!commentRepo.existsById(id)) {
            throw new CommentNotFoundException(id);
        }
        commentRepo.deleteById(id);
    }
    
    public List<Comment> findByPost(Post post) {
        return commentRepo.findByPost(post);
    }
    
    
}
