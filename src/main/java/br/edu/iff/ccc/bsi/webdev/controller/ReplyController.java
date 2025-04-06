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

import br.edu.iff.ccc.bsi.webdev.dto.ReplyDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.services.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/reply")
@Tag(name = "Replies", description = "API para gerenciamento de respostas (Replies)")
public class ReplyController {
	
	@Autowired
    private ReplyService replyService;
	
	@Autowired
	    private ReplyDTO replyDTO;
    
    @PostMapping("/create")
    @Operation(summary = "Criar uma nova resposta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resposta criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public EntityModel<Reply> createReply(@RequestBody Reply reply) {
        Reply createdReply = replyService.save(reply);
        return replyDTO.toModel(createdReply);
    }
    
    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar uma resposta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resposta encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Resposta não encontrada")
    })
    public EntityModel<Reply> getReply(@PathVariable Long id) {
        Reply reply = replyService.findById(id);
        return replyDTO.toModel(reply);
    }
    
    @GetMapping("/all")
    @Operation(summary = "Listar todas as respostas")
    public CollectionModel<EntityModel<Reply>> getAllReplies() {
        List<EntityModel<Reply>> replies = replyService.findAll().stream()
                .map(replyDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(replies, linkTo(methodOn(ReplyController.class).getAllReplies()).withSelfRel());
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar uma resposta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resposta atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Resposta não encontrada")
    })
    public EntityModel<Reply> updateReply(@PathVariable Long id, @RequestBody Reply replyDetails) {
        Reply updatedReply = replyService.update(id, replyDetails);
        return replyDTO.toModel(updatedReply);
    }
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar uma resposta por ID")
    public void deleteReply(@PathVariable Long id) {
        replyService.deleteById(id);
    }

    
}
