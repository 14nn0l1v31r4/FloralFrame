package br.edu.iff.ccc.bsi.webdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.exception.AdmNotFoundException;
 import br.edu.iff.ccc.bsi.webdev.repository.AdmRepository;

@Service
public class AdmService {
	
	@Autowired
	private AdmRepository admRepo;
	
	public Adm findById(Long id){
		Adm adm = admRepo.findById(id).orElseThrow(() -> new AdmNotFoundException(id));
		return adm;
	}
	
	 public Adm save(Adm adm) {
	        return admRepo.save(adm);
	    }
	 
	 public void deleteById(Long id) {
	        if (!admRepo.existsById(id)) {
	            throw new AdmNotFoundException(id);
	        }
	        admRepo.deleteById(id);
	    }
	 
	 public Adm update(Long id, Adm adm ) {
	        if (!admRepo.existsById(id)) {
	            throw new AdmNotFoundException(id);
	        }
	        return admRepo.save(adm);
	    }
	 
	 public List<Adm> findAll() {
		 return admRepo.findAll();
	 }
}
