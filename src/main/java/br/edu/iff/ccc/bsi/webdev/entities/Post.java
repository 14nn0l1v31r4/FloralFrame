package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "post_tb")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title")
	@NotNull(message = "Title cannot be null")
	private String title;
	
	@Column(name = "body")
	@NotNull(message = "Body cannot be null")
	private String body;
	
	@Column(name = "date")
	@PastOrPresent(message = "Date cannot be future")
	private LocalDate date;
	
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category cannot be null")
	private CategoryPost category;
    
    @Column(name = "like_bool")
    private boolean like_bool;
    
    @Column(name = "userID", nullable = false)
    private Long userID;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserComum author;


    
	public Post(Long userID, String title, String body, CategoryPost category) {
		super();
		this.userID = userID;
		this.title = title;
		this.body = body;
		this.date = LocalDate.now();
		this.category = category;
	}

	
	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public Post() {

	}

	public LocalDate getDate() {
		return date;
	}

	


	public void setDate(LocalDate date) {
		this.date = date;
	}




	public CategoryPost getCategory() {
		return category;
	}




	public void setCategory(CategoryPost category) {
		this.category = category;
	}




	public boolean isLike_bool() {
		return like_bool;
	}




	public void setLike_bool(boolean like_bool) {
		this.like_bool = like_bool;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}




	public Long getId() {
		return id;
	}

	
	
	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}
	
	public void setAuthor(UserComum author) {
		this.author = author;
	}

	public UserComum getAuthor() {
		return author;
	}
	
}