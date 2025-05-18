package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "reply_tb")
public class Reply extends Interaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	@NotNull(message = "Content cannot be null")
	private String content;
	
	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;
	
	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserComum author;

	 @ManyToOne
	 @JoinColumn(name = "comment_id", nullable = false)
	 private Comment comment;
	 
	 public Reply(UserComum author, boolean like, LocalDate createdAt, String content, Comment comment) {
	        super(like, createdAt);
	        this.author = author;
	        this.content = content;
	        this.createdAt = createdAt;
	        this.comment = comment;
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

	public UserComum getAuthor() {
		return author;
	}

	public void setAuthor(UserComum author) {
		this.author = author;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public Long getId() {
		return super.id;
	}
	
}
