package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends Interaction implements Serializable{
	
	public Comment(boolean like, LocalDate createdAt) {
		super(like, createdAt);
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String content;
	
	@Column
	private LocalDate createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserComum userIdComment;

    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public void setUserIdComment(UserComum userId) {
		this.userIdComment = userId;
	}

	public Comment(UserComum userIdComment, boolean like, LocalDate createdAt, String content, Post post) {
		super(like, createdAt);
		this.userIdComment = userIdComment;
		this.content = content;
		this.post = post;
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

	
	public UserComum getUserIdComment() {
		return userIdComment;
	}
}
