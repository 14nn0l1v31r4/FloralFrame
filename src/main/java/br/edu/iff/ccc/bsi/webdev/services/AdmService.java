package br.edu.iff.ccc.bsi.webdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.repository.AdmRepository;

@Service
public class AdmService {
	
	@Autowired
	private AdmRepository admRepo;
	
	public Adm findById(Long id){
		Adm adm = admRepo.findById(id).orElseThrow(null);
		return adm;
	}
	
	 public Adm save(Adm adm) {
	        return admRepo.save(adm);
	    }
	 
	 public void deleteById(Long id) {
	        if (!admRepo.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para exclusão");
	        }
	        admRepo.deleteById(id);
	    }
	 
	 public Adm update(Long id, Adm adm ) {
	        if (!admRepo.existsById(id)) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado para atualização");
	        }
	        return admRepo.save(adm);
	    }
	 
	 public List<Adm> findAll() {
		 return admRepo.findAll();
	 }
}
