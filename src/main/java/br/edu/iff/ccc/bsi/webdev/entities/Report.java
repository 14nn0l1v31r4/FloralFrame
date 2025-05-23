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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "report_tb")
public class Report implements Serializable{

	private static final long serialVersionUID = 1L;
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne 
    @JoinColumn(name = "reporter_id", nullable = false)
    @NotNull(message = "User cannot be null")
    private UserComum reporter; // Quem fez a denúncia

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @NotNull(message = "Post cannot be null")
    private Post post; // Post denunciado

    @Column(nullable = false)
    @NotNull(message = "Reason cannot be null")
    private String reason; // Motivo da denúncia

    @Column(nullable = false)
    @PastOrPresent(message = "Date cannot be future")
    private LocalDate reportDate;
    
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment; // pode ser nulo

    @ManyToOne
    @JoinColumn(name = "reply_id")
    private Reply reply;

    public Report() {}

    public Report(UserComum reporter, Post post, String reason) {
        this.reporter = reporter;
        this.post = post;
        this.reason = reason;
        this.reportDate = LocalDate.now();
    }
    public Report(UserComum reporter, Comment comment, String reason, Post post) {
    	this.reporter = reporter;
    	this.comment = comment;
    	this.post = post;
    	this.reason = reason;
    	this.reportDate = LocalDate.now();
    }
    public Report(UserComum reporter, Reply reply, String reason, Comment comment, Post post) {
    	this.reporter = reporter;
    	this.reply = reply;
    	this.comment = comment;
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

	public Long getId() {
		return id;
	}

	public UserComum getReporter() {
		return reporter;
	}

	public void setReporter(UserComum reporter) {
		this.reporter = reporter;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

    
    

}
