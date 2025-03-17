package br.edu.iff.ccc.bsi.webdev.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.webdev.entities.Community;
import br.edu.iff.ccc.bsi.webdev.repository.CommunityRepository;
import br.edu.iff.ccc.bsi.webdev.services.CommunityService;

@ExtendWith(MockitoExtension.class)
public class CommunityTest {
	@InjectMocks
	private CommunityService communityService;
	
	@Mock
	private CommunityRepository communityRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
}
	@Test
	@DisplayName("Busca por Id em CommunityService com sucesso.")
    void testFindById() {
        
         Community mockedCommunity = new Community(1L, "Comunidade", " ", 1);
         when(communityRepo.findById(1L)).thenReturn(Optional.of(mockedCommunity));
        
        // 
         Community result = communityService.findById(1L);
        
        //
        assertNotNull(result);
        assertEquals("Cominidade", result.getName());
        assertEquals("", result.getDescription());
        verify(communityRepo).findById(1L); 
        
    }

}
