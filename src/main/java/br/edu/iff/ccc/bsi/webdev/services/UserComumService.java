package br.edu.iff.ccc.bsi.webdev.services;


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
	        user.getId();
	        return userRepo.save(user);
	    }
}