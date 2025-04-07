package br.edu.iff.ccc.bsi.webdev.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.repository.UserComumRepository;

@Service
public class UserComumService{

	@Autowired
	private UserComumRepository userRepo;
	
	public UserComum findById(Long id){
		UserComum user = userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
		return user;
	}
	
	 public UserComum save(UserComum user) {
	        return userRepo.save(user);
	    }
	 
	 public UserComum update(Long id, UserComum user) {
	        if (!userRepo.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para atualização");
	        }
	        return userRepo.save(user);
	    }
	 
	 
	 public void deleteById(Long id) {
	        if (!userRepo.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão");
	        }
	        userRepo.deleteById(id);
	    }
	 
	 public List<UserComum> findByName(String name) {
		    return userRepo.findByName(name);
		}
	 
	 public UserComum findByEmail(String email){
		 return userRepo.findByEmail(email);
	 }
	 
	 public List<UserComum> findAll() {
		    return userRepo.findAll();
		}
}