package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query("SELECT n FROM Post n WHERE n.title = :title")
	 List<Post> findByTitle(@Param("title") String title);
	
	List<Post> findByUserID(UserComum userID);

}
