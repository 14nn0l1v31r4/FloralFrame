package br.edu.iff.ccc.bsi.webdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.services.AdmService;

@RestController
@RequestMapping("api/v1/adm")
public class AdmContoller {
	
	@Autowired
    private AdmService admService;

    // Endpoint para criar um novo usuário
    @PostMapping("/create")
    public Adm createAdm(@RequestBody Adm adm) {
        return admService.save(adm);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Adm getAdm(@PathVariable Long id) {
        return admService.findById(id);
    }
    
    @GetMapping("/all")
    public List<Adm> getAllAdms() {
        return admService.findAll();
    }
    
    @PutMapping("/update/{id}")
    public Adm updateAdm(@PathVariable Long id, @RequestBody Adm admDetails) {
        return admService.update(id, admDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteAdm(@PathVariable Long id) {
        admService.deleteById(id);
    }
}
