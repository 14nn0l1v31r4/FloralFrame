package br.edu.iff.ccc.bsi.webdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;

@RestController
@RequestMapping("/users")
public class UserComumController {

    @Autowired
    private UserComumService userComumService;

    // Endpoint para criar um novo usuário
    @PostMapping
    public UserComum createUser(@RequestBody UserComum user) {
        return userComumService.save(user);
    }

    // Endpoint para buscar um usuário por ID
    @GetMapping("/{id}")
    public UserComum getUser(@PathVariable Long id) {
        return userComumService.findById(id);
    }


}
