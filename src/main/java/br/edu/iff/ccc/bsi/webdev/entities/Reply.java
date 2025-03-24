package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reply extends Interaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@Column
	private String content;
	
	@Column
	private LocalDate createdAt;

	public Reply(boolean like, LocalDate createdAt, String content) {
		super(like, createdAt);
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Reply() {
       
    }
	
	public Reply(boolean like, LocalDate createdAt) {
		super(like, createdAt);
		
	}

}
