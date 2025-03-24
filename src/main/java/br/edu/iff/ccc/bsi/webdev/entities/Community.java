package br.edu.iff.ccc.bsi.webdev.entities;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Community_tb")
public class Community implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column
	@NotNull(message = "Name cannot be null")
	private String name;
	
	@Column
	@NotNull(message = "Description cannot be null")
	private String description;
	
	@Column
	private int membersQuantity;

	public Community(Long id, String name, String description, int membersQuantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.membersQuantity = membersQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
