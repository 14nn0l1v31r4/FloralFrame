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

import br.edu.iff.ccc.bsi.webdev.dto.CommunityDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/community")
@Tag(name = "Comunidades", description = "API para gerenciamento de comunidades")
public class CommunityController {
	
	@Autowired
    private CommunityService communityService;
	
	@Autowired
    private CommunityDTO communityDTO;

    @PostMapping()
    @Operation(summary = "Criar uma nova comunidade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comunidade criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public EntityModel<Community> createCommunity(@RequestBody Community community) {
        Community createdCommunity = communityService.save(community);
        return communityDTO.toModel(createdCommunity);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar comunidade por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comunidade encontrada"),
        @ApiResponse(responseCode = "404", description = "Comunidade não encontrada")
    })
    public EntityModel<Community> getCommunity(@PathVariable Long id) {
        Community community = communityService.findById(id);
        return communityDTO.toModel(community);
    }
    
    @GetMapping()
    @Operation(summary = "Listar todas as comunidades")
    public CollectionModel<EntityModel<Community>> getAllCommunities() {
        List<EntityModel<Community>> communities = communityService.findAll().stream()
                .map(communityDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(communities, linkTo(methodOn(CommunityController.class).getAllCommunities()).withSelfRel());
    }
    
    @GetMapping("/name/{name}")
    @Operation(summary = "Buscar comunidades pelo nome")
    public List<Community> getCommunitiesByName(@PathVariable String name) {
        return communityService.findByName(name);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de uma comunidade")
    public EntityModel<Community> updateCommunity(@PathVariable Long id, @RequestBody Community communityDetails) {
        Community updatedCommunity = communityService.update(id, communityDetails);
        return communityDTO.toModel(updatedCommunity);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma comunidade por ID")
    public void deleteCommunity(@PathVariable Long id) {
        communityService.deleteById(id);
    }

}
