package br.edu.iff.ccc.bsi.webdev.controller;

	import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.dto.UserComumDTO;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "Users", description = "API para gerenciamento de usuários comuns")
public class UserComumController {

    @Autowired
    private UserComumService userComumService;
    
    private UserComumDTO userDTO;

    @PostMapping("/create")
    @Operation(summary = "Criar um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    public EntityModel<UserComum> createUser(@Valid @RequestBody UserComum user) {
        UserComum createdUser = userComumService.save(user);
        return userDTO.toModel(createdUser);
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public EntityModel<UserComum> getUser(@PathVariable Long id) {
        UserComum user = userComumService.findById(id);
        return userDTO.toModel(user);
    }
    
    @GetMapping("/search/{name}")
    @Operation(summary = "Buscar usuários por nome")
    public CollectionModel<EntityModel<UserComum>> getUsersByName(@PathVariable String name) {
        List<EntityModel<UserComum>> users = userComumService.findByName(name).stream()
                .map(userDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserComumController.class).getAllUsers()).withSelfRel());
    }
    
    @GetMapping("/all")
    @Operation(summary = "Listar todos os usuários")
    public CollectionModel<EntityModel<UserComum>> getAllUsers() {
        List<EntityModel<UserComum>> users = userComumService.findAll().stream()
                .map(userDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserComumController.class).getAllUsers()).withSelfRel());
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public EntityModel<UserComum> updateUser(@PathVariable Long id, @RequestBody UserComum userDetails) {
        UserComum updatedUser = userComumService.update(id, userDetails);
        return userDTO.toModel(updatedUser);
    }
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Excluir um usuário")
    public void deleteUser(@PathVariable Long id) {
        userComumService.deleteById(id);
    }
    
}
