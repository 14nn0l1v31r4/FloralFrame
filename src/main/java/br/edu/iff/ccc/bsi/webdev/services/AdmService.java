package br.edu.iff.ccc.bsi.webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.repository.AdmRepository;

@Service
public class AdmService {
	
	@Autowired
	private AdmRepository AdmRepo;
	
	public Adm findById(Long id){
		Adm adm = AdmRepo.findById(id).orElseThrow(null);
		return adm;
	}
	
	 public Adm save(Adm adm) {
	        return AdmRepo.save(adm);
	    }
	 

}
