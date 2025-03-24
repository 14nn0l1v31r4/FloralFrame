package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public abstract class Interaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private boolean like;
	
	@Column
	private LocalDate createdAt;
	
	@ManyToOne
    @JoinColumn(name = "post_id") // Nome da chave estrangeira	
	private Post post;


	public Interaction(boolean like, LocalDate createdAt) {
		super();
		this.like = like;
		this.createdAt = createdAt;
	}
	
	public Interaction() {
		
	}
	

}
