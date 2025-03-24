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

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;

@RestController
@RequestMapping("api/v1/user")
public class UserComumController {

    @Autowired
    private UserComumService userComumService;

    // Endpoint para criar um novo usuário
    @PostMapping("/create")
    public UserComum createUser(@RequestBody UserComum user) {
        return userComumService.save(user);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/search/{id}")
    public UserComum getUser(@PathVariable Long id) {
        return userComumService.findById(id);
    }
    
    @GetMapping("/search/{name}")
    public List<UserComum> getUsersByName(@PathVariable String name) {
    	return userComumService.findByName(name);
    }
    
    @GetMapping("/all")
    public List<UserComum> getAllUsers() {
        return userComumService.findAll();
    }
    
    @PutMapping("/update/{id}")
    public UserComum updateUser(@PathVariable Long id, @RequestBody UserComum userDetails) {
        return userComumService.update(id, userDetails);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userComumService.deleteById(id);
    }
    
}
