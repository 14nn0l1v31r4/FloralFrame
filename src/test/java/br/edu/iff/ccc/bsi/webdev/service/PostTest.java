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

import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.enums.CategoryPost;
import br.edu.iff.ccc.bsi.webdev.repository.PostRepository;
import br.edu.iff.ccc.bsi.webdev.services.PostService;

@ExtendWith(MockitoExtension.class)
public class PostTest {
	
	@InjectMocks
	private PostService postService;
	
	@Mock
	private PostRepository postRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
}
	@Test
	@DisplayName("Busca por Id em PostService com sucesso.")
    void testFindById() {
		
        
         Post mockedPost = new Post(1L,"titulo",  "tal tal tal", CategoryPost.AQUATICA);
         when(postRepo.findById(1L)).thenReturn(Optional.of(mockedPost));
        
        // 
        Post result = postService.findById(1L);
        
        //
        assertNotNull(result);
        assertEquals("titulo", result.getTitle());
        assertEquals("tal tal", result.getBody());
        verify(postRepo).findById(1L); 
        verify(postService).findByTitle("titulo"); 
        
    }


}
