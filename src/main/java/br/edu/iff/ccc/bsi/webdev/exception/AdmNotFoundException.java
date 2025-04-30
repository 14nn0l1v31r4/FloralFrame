package br.edu.iff.ccc.bsi.webdev.exception;


public class AdmNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public AdmNotFoundException(Long id) {
		super("Administrador não Encontrado");
	}

}
