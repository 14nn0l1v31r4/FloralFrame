package br.edu.iff.ccc.bsi.webdev.exception;

public class CommunityNotFoundException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public CommunityNotFoundException(Long id) {
		super("Comunidade não Encontrado");
	}

}
