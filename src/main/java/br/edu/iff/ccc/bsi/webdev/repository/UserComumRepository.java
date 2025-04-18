package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;

@Repository
public interface UserComumRepository extends JpaRepository<UserComum, Long> {

	@Query("SELECT n FROM UserComum n WHERE n.name = :name")
	List<UserComum> findByName(@Param("name") String name);
	
	@Query("SELECT u FROM UserComum u WHERE u.email = :email")
	UserComum findByEmail(@Param("email") String email);
}
