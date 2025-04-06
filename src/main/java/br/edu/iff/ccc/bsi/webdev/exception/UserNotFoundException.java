package br.edu.iff.ccc.bsi.webdev.exception;


public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(Long id) {
		super("Usuário não Encontrado");
	}

}
