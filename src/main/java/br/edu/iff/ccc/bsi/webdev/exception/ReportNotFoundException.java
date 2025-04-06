package br.edu.iff.ccc.bsi.webdev.exception;

public class ReportNotFoundException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	public ReportNotFoundException(Long id) {
		super("Report not found");
	}

}
