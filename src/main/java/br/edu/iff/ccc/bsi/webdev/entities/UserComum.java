package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "UserComum_tb")
public class UserComum extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	@NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "phone")
    @NotNull(message = "Phone cannot be null")
    private String phone;
    

	public UserComum(String name, String email, String password, String phone, Long id) {
		super(name, email, password, phone);
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public UserComum() {
		super();
	}
 
	

}
