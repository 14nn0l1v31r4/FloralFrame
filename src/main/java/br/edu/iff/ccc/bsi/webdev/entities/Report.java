package br.edu.iff.ccc.bsi.webdev.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Report implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Relacionamento: um usuário pode fazer várias denúncias
    @JoinColumn(name = "reporter_id", nullable = false)
    private UserComum reporter; // Quem fez a denúncia

    @ManyToOne // Relacionamento: um post pode ser denunciado várias vezes
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // Post denunciado

    @Column(nullable = false)
    private String reason; // Motivo da denúncia

    @Column(nullable = false)
    private LocalDate reportDate;

    public Report() {}

    public Report(UserComum reporter, Post post, String reason) {
        this.reporter = reporter;
        this.post = post;
        this.reason = reason;
        this.reportDate = LocalDate.now();
    }

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
    

}
