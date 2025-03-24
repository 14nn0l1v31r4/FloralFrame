package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
	
	@Query("SELECT n FROM Community n WHERE n.name = :name")
	 List<Community> findByName(@Param("name") String name);

}
