package br.edu.iff.ccc.bsi.webdev.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import br.edu.iff.ccc.bsi.webdev.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;

	
	public Post findById(Long id){
		Post post = postRepo.findById(id).orElseThrow(null);
		return post;
	}
	
	public Post createPost(String title, String body, CategoryPost category) {
		
		 Post post = new Post(title, body, category);
	        return postRepo.save(post);
	}
	
	public Post save(Post post) {
        return postRepo.save(post);
    }
	
	public List<Post> findByPost(){
		List<Post> posts = new ArrayList<>();
		return posts;
	}
	
	public List<Post> findAll() {
        return postRepo.findAll();
    }
	
	public List<Post> findByTitle(String title) {
        return postRepo.findByTitle(title);
    }
	
	public Post update(Long id, Post postDetails) {
        Post post = findById(id);
        post.setTitle(postDetails.getTitle());
        post.setBody(postDetails.getBody());
        post.setDate(postDetails.getDate());
        post.setCategory(postDetails.getCategory());
        return postRepo.save(post);
    }
	
	public void deleteById(Long id) {
        if (!postRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado para exclusão");
        }
        postRepo.deleteById(id);
    }

}
