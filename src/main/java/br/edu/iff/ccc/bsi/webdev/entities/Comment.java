package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "Comment_tb")
public class Comment extends Interaction implements Serializable {

	private static final long serialVersionUID = 1L;


	@Column(nullable = false)
	private String content;

	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserComum author;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

	public Comment() {
		super();
	}

	public Comment(UserComum author, boolean like, LocalDate createdAt, String content, Post post) {
		super(like, createdAt);
		this.author = author;
		this.content = content;
		this.createdAt = createdAt;
		this.post = post;
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

	public UserComum getAuthor() {
		return author;
	}

	public void setAuthor(UserComum author) {
		this.author = author;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	
	
}
