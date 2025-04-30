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

import br.edu.iff.ccc.bsi.webdev.dto.AdmDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.services.AdmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/adm")
@Tag(name = "Administrador", description = "API para gerenciamento de administradores")
public class AdmController {
	
	@Autowired
    private AdmService admService;
	
	@Autowired
    private AdmDTO admDTO;

    @PostMapping()
    @Operation(summary = "Criar administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public EntityModel<Adm> createAdm(@RequestBody Adm adm) {
        Adm createdAdm = admService.save(adm);
        return admDTO.toModel(createdAdm);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar administrador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado")
    })
    public EntityModel<Adm> getAdm(@PathVariable Long id) {
        Adm adm = admService.findById(id);
        return admDTO.toModel(adm);
    }
    
    @GetMapping()
    @Operation(summary = "Listar todos os administradores")
    public CollectionModel<EntityModel<Adm>> getAllAdms() {
        List<EntityModel<Adm>> adms = admService.findAll().stream()
                .map(admDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(adms, linkTo(methodOn(AdmController.class).getAllAdms()).withSelfRel());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar administrador")
    public EntityModel<Adm> updateAdm(@PathVariable Long id, @RequestBody Adm admDetails) {
        Adm updatedAdm = admService.update(id, admDetails);
        return admDTO.toModel(updatedAdm);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar administrador")
    public void deleteAdm(@PathVariable Long id) {
        admService.deleteById(id);
    }
}
