package br.edu.iff.ccc.bsi.webdev.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.exception.ReplyNotFoundException;
import br.edu.iff.ccc.bsi.webdev.repository.ReplyRepository;

@Service
public class ReplyService {
    
    @Autowired
    private ReplyRepository replyRepo;

    // Método para buscar uma resposta por ID
    public Reply findById(Long id) {
        return replyRepo.findById(id).orElseThrow(() -> new ReplyNotFoundException(id));
    }

    // Método para criar uma nova resposta
    public Reply createReply(String content, boolean like, LocalDate createdAt) {
        Reply reply = new Reply(like, createdAt);
        reply.setContent(content);
        return replyRepo.save(reply);
    }

    // Método para salvar uma resposta
    public Reply save(Reply reply) {
        return replyRepo.save(reply);
    }
    
    public List<Reply> findAll() {
	    return replyRepo.findAll();
	}
    
    public Reply update(Long id, Reply replyDetails) {
		if (!replyRepo.existsById(id)) {
			throw new ReplyNotFoundException(id);
		}
        Reply reply = findById(id);
        reply.setContent(replyDetails.getContent());
        reply.setCreatedAt(replyDetails.getCreatedAt());
        return replyRepo.save(reply);
    }
    
    public void deleteById(Long id) {
        if (!replyRepo.existsById(id)) {
            throw new ReplyNotFoundException(id);
        }
        replyRepo.deleteById(id);
    }
}
