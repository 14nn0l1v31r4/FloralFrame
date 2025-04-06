package br.edu.iff.ccc.bsi.webdev.exception;

public class ReplyNotFoundException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public ReplyNotFoundException(Long id) {
		super("Reply not found");
	}

}
