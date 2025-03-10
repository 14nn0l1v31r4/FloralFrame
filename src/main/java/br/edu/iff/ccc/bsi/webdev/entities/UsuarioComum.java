package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class UsuarioComum extends Pessoa implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	public UsuarioComum(String name, String email, String password, String phone, Long id) {
		super(name, email, password, phone);
		this.id = id;
	}


}
