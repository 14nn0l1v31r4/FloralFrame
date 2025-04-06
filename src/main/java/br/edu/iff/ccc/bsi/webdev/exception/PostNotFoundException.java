package br.edu.iff.ccc.bsi.webdev.exception;

public class PostNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public PostNotFoundException(Long id) {
		super("Post não Encontrado");
	}

}
