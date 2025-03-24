package br.edu.iff.ccc.bsi.webdev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

	
}
