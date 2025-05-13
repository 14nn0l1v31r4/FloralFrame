package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
    List<Comment> findByContent(@Param("content") String content);
    
    List<Comment> findByPost(Post post);
    
    List<Comment> findByUserComumId(UserComum userComumId);

}
