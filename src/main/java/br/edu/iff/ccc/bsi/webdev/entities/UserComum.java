package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "UserComum_tb")
public class UserComum extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "phone")
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
	
	
 
	

}
