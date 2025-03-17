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

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.repository.AdmRepository;
import br.edu.iff.ccc.bsi.webdev.services.AdmService;

@ExtendWith(MockitoExtension.class)
public class AdmTest {
	@InjectMocks
	private AdmService admService;
	
	@Mock
	private AdmRepository admRepo;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
}
	@Test
	@DisplayName("Busca por Id em AdmService com sucesso.")
    void testFindById() {
        
         Adm mockedAdm = new Adm("fulanoadm", "fulanoadm@mail.com", "123", "2127878");
         when(admRepo.findById(1L)).thenReturn(Optional.of(mockedAdm));
        
        // 
        Adm result = admService.findById(1L);
        
        //
        assertNotNull(result);
        assertEquals("Fulano", result.getName());
        assertEquals("", result.getEmail());
        verify(admRepo).findById(1L); 
        
    }

}
