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

import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.repository.UserComumRepository;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;

@ExtendWith(MockitoExtension.class)
public class UserComumTest {

	@InjectMocks
	private UserComumService userService;
	
	@Mock
	private UserComumRepository userRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
}
	@Test
	@DisplayName("Busca por Id em UserService com sucesso.")
    void testFindById() {
        
         UserComum mockedUser = new UserComum("fulano", "fulano@mail.com", "123", "2127878");
         when(userRepo.findById(1L)).thenReturn(Optional.of(mockedUser));
        
        // 
        UserComum result = userService.findById(1L);
        
        //
        assertNotNull(result);
        assertEquals("Fulano", result.getName());
        assertEquals("", result.getEmail());
        verify(userRepo).findById(1L); 
        verify(userRepo).findByName("fulano"); 
        
    }
}
