package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
	@Table(name = "category_post_tb")
public class CategoryPostEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "name", unique = true, nullable = false)
	    private String name;

	    @Column(name = "description")
	    private String description; 

	    
	    public CategoryPostEntity() {}

	    public CategoryPostEntity(String name, String description) {
	        this.name = name;
	        this.description = description;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

}
