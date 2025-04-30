package br.edu.iff.ccc.bsi.webdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.repository.ReplyRepository;
import br.edu.iff.ccc.bsi.webdev.services.ReplyService;

@ExtendWith(MockitoExtension.class)
public class Replytest {
	
	@InjectMocks
	private ReplyService replyService;
	
	@InjectMocks
	private ReplyRepository replyRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	@DisplayName("Buscar por Id em ReplyService com sucesso.")
	void testFindById() {
		Reply mockedReply = new Reply(1L,true, LocalDate.now(), "teste" );
		 when(replyRepo.findById(1L)).thenReturn(Optional.of(mockedReply));
		
		 Reply result = replyService.findById(1L);
		 
		 assertNotNull(result);
	        assertEquals("Fulano", result.getContent());
	        assertEquals("", result.getCreatedAt());
	        verify(replyRepo).findById(1L); 
		 
	}

}
