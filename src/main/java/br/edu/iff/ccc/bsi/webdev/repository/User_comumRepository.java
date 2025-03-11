package br.edu.iff.ccc.bsi.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.webdev.entities.User_comum;

@Repository
public interface User_comumRepository extends JpaRepository<User_comum, Long> {

}
