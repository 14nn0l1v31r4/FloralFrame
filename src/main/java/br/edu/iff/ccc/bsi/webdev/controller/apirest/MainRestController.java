package br.edu.iff.ccc.bsi.webdev.controller.apirest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "MainRest", description = "Exemplo de um Rest Controller")
public class MainRestController {
	
	@Operation(summary = "Retorna todos os exemplos armazenados")
	@ApiResponses({
		@ApiResponse(responseCode = "200")
		
	})
	@GetMapping(path = "/exemplos")
	public ResponseEntity<List<Map<String, String>>> getExemplo() {
		List<Map<String, String>> listaEX = new ArrayList<>();
		Map<String, String> item = new HashMap<>();
		item.put("Exemplo 1", "=> 1"); 
		item.put("Exemplo 2", "=> 2"); 
		listaEX.add(item);
		return ResponseEntity.ok(listaEX);
	}
	
	@Operation(summary = "Retorna um exemplo por ID")
	@GetMapping("exemplos/{id}")
	public ResponseEntity<String> getExemploId(@PathVariable("id") int id) {
		return ResponseEntity.ok()
				.header("Content-Type", "application/json")
				.body("Exemplo => " + id);
	}
	
	@Operation(summary = "Atualiza um exemplo por ID ")
	@PutMapping(path= "exemplos/{id}")
	public String postExemploId(@PathVariable("id") int idx, @RequestParam("nome") String nome) {
		return "id => " + idx + " nome => " + nome;
	}
	
}

