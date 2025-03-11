package br.edu.iff.ccc.bsi.webdev.entities;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Community implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
}
