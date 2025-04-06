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
public class UserComum extends Person {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 100)
	@NotNull(message = "Name cannot be null")
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    @NotNull(message = "Email should be valid")
    private String email;

    @Column(name = "phone")
    @NotNull(message = "Phone cannot be null")
    private String phone;
    
    @NotNull(message = "Password cannot be null")
    private String password;

	public UserComum(String name, String email, String password, String phone) {
		super(name, email, password, phone);
	}
	
	public Long getId() {
		return id;
	}

	
	public UserComum() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 
	

}
