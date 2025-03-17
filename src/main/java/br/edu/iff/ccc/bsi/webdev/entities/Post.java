package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.sql.Date;

import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Post_tb")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "date")
	private Date date;
	

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
	private CategoryPostEntity category;

	public Post(Long id, String title, String body, Date date, CategoryPostEntity category) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
		this.category = category;
	}
	
	public Post(String title, String body, Date date, CategoryPostEntity category) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.date = date;
		this.category = category;
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
	
	
	
	
	
}