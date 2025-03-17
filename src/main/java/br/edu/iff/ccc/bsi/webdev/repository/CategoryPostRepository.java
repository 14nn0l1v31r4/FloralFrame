package br.edu.iff.ccc.bsi.webdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iff.ccc.bsi.webdev.entities.CategoryPostEntity;

public interface CategoryPostRepository extends JpaRepository<CategoryPostEntity, Long> {

}
