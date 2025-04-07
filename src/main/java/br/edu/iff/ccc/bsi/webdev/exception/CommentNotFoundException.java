package br.edu.iff.ccc.bsi.webdev.exception;

public class CommentNotFoundException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public CommentNotFoundException(Long id) {
		super("Comment not found");
	}

}
