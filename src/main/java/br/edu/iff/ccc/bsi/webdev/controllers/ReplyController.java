package br.edu.iff.ccc.bsi.webdev.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.services.ReplyService;

@RestController
@RequestMapping("api/v1/reply")
public class ReplyController {
	
	@Autowired
    private ReplyService replyService;
	

    // Endpoint para criar um novo post


    // Endpoint para buscar um post por ID
    @GetMapping("/{id}")
    public Reply getReply(@PathVariable Long id) {
        return replyService.findById(id);
    }
    
    @PostMapping("/create")
    public Reply createReply(@RequestParam String content, 
                           @RequestParam boolean like, 
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createAt)
                           {
        return replyService.createReply(content, like, LocalDate.now() );
    }
    
    @PutMapping("/update/{id}")
    public Reply updateReply(@PathVariable Long id, @RequestBody Reply replyDetails) {
        return replyService.update(id, replyDetails);
    }
    
    // Excluir uma resposta
    @DeleteMapping("/delete/{id}")
    public void deleteReply(@PathVariable Long id) {
        replyService.deleteById(id);
    }

    
}
