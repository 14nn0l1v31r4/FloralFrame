package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Comment extends Interaction implements Serializable{
	
	public Comment(boolean like, LocalDate createdAt) {
		super(like, createdAt);
	}

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column
	private String content;
	
	@Column
	private LocalDate createdAt;
	
    @JoinColumn(name = "user_id")
    private Long userIdComment;

	public Comment(Long userIdComment, boolean like, LocalDate createdAt, String content) {
		super(like, createdAt);
		this.userIdComment = userIdComment;
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

	public Comment() {
		
	}

	
	public Long getUserIdComment() {
		return userIdComment;
	}
}
