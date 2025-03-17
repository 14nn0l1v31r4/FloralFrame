package br.edu.iff.ccc.bsi.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
    private CommunityService communityService;

    // Endpoint para criar um novo usuário
    @PostMapping
    public Community createCommunity(@RequestBody Community community) {
        return communityService.save(community);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Community getCommunity(@PathVariable Long id) {
        return communityService.findById(id);
    }

}
