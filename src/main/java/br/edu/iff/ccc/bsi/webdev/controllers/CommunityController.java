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

import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;

@RestController
@RequestMapping("api/v1/community")
public class CommunityController {
	
	@Autowired
    private CommunityService communityService;

    // Endpoint para criar um novo usuário
    @PostMapping("/create")
    public Community createCommunity(@RequestBody Community community) {
        return communityService.save(community);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public Community getCommunity(@PathVariable Long id) {
        return communityService.findById(id);
    }
    
    @GetMapping("/all")
    public List<Community> getAllCommunities() {
        return communityService.findAll();
    }
    
    @GetMapping("/search/{name}")
    public List<Community> getCommunitiesByName(@PathVariable String name) {
        return communityService.findByName(name);
    }
    
    @PutMapping("/update/{id}")
    public Community updateCommunity(@PathVariable Long id, @RequestBody Community communityDetails) {
        return communityService.update(id, communityDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteCommunity(@PathVariable Long id) {
        communityService.deleteById(id);
    }

}
