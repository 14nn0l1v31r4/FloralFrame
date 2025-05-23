package br.edu.iff.ccc.bsi.webdev.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.exception.UserNotFoundException;
import br.edu.iff.ccc.bsi.webdev.repository.UserComumRepository;

@Service
public class UserComumService{

	@Autowired
	private UserComumRepository userRepo;
	
	public UserComum findById(Long id){
		UserComum user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}
	
	 public UserComum save(UserComum user) {
	        return userRepo.save(user);
	    }
	 
	 public UserComum update(Long id, UserComum user) {
	        if (!userRepo.existsById(id)) {
	            throw new UserNotFoundException(id);
	        }
	        return userRepo.save(user);
	    }
	 
	 
	 public void deleteById(Long id) {
	        if (!userRepo.existsById(id)) {
	            throw new UserNotFoundException(id);
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
	 
	 public UserComum findById(UserComum userComum) {
		    if (userComum != null && userComum.getId() != null) {
		        return findById(userComum.getId()); // Busca o usuário pelo ID
		    }
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário ou ID inválido");
		}

}