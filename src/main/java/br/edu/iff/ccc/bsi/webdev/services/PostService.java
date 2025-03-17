package br.edu.iff.ccc.bsi.webdev.services;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.CategoryPostEntity;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.repository.CategoryPostRepository;
import br.edu.iff.ccc.bsi.webdev.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;

	@Autowired
	private CategoryPostRepository categoryPostRepository;
	
	public Post findById(Long id){
		Post post = postRepo.findById(id).orElseThrow(null);
		return post;
	}
	
	public Post createPost(Long id, String title, String body, Date date, Long category) {
		CategoryPostEntity categoryEntity = categoryPostRepository.findById(category).orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));
		 Post post = new Post(title, body, date, categoryEntity);
	        return postRepo.save(post);
	}
	
	public Post save(Post post) {
        return postRepo.save(post);
    }
	
	
	

}
