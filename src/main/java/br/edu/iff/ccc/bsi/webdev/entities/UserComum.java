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

	public UserComum(String name, String email, String password, String phone) {
		super(name, email, password, phone);
	}

	public UserComum() {
		super();
	}
	
	public UserComum getUser() {
        return this;
    }
	

}
