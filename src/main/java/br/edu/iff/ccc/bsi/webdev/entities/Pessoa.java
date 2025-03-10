package br.edu.iff.ccc.bsi.webdev.entities;

import java.util.Objects;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Pessoa {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
		protected String name;
		protected String email;
		protected String password;
		protected String phone;
		
		public Pessoa(String name, String email, String password, String phone) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.phone = phone;
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(name);
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pessoa other = (Pessoa) obj;
			return Objects.equals(name, other.name);
		}
	
	

}
