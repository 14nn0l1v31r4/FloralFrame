package br.edu.iff.ccc.bsi.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.services.AdmService;

@RestController
@RequestMapping("/adm")
public class AdmContoller {
	
	@Autowired
    private AdmService admService;

    // Endpoint para criar um novo usuário
    @PostMapping
    public Adm createAdm(@RequestBody Adm adm) {
        return admService.save(adm);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Adm getUser(@PathVariable Long id) {
        return admService.findById(id);
    }
}
