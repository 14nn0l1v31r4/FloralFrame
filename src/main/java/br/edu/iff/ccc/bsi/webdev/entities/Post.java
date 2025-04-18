package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "Post_tb")
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
    
    
    private boolean like_bool;

	public Post(String title, String body, CategoryPost category) {
		super();
		this.title = title;
		this.body = body;
		this.date = LocalDate.now();
		this.category = category;
	}
	
	public Post(String title, String body, LocalDate date, CategoryPost category) {
		super();
		this.title = title;
		this.body = body;
		this.date = LocalDate.now();
		this.category = category;
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



	
	
	
	
	
}