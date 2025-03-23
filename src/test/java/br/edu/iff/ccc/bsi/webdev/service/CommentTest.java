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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.repository.CommentRepository;
import br.edu.iff.ccc.bsi.webdev.services.CommentService;

@ExtendWith(MockitoExtension.class)
public class CommentTest {
	
	@InjectMocks
	private CommentService commentService;
	
	@Mock
	private CommentRepository commentRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
}
	
	@Test
	@DisplayName("Busca por Id em CommentService com sucesso.")
	void testFindById() {
        
        Comment mockedComment = new Comment(true, LocalDate.now(), "comenta´rio tal");
        when(commentRepo.findById(1L)).thenReturn(Optional.of(mockedComment));
       
       // 
        Comment result = commentService.findById(1L);
       
       //
       assertNotNull(result);
       assertEquals("Fulano", result.getContent());
       assertEquals("", result.getCreatedAt());
       verify(commentRepo).findById(1L); 
       
   }

}
