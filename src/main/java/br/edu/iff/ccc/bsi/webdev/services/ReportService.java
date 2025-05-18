package br.edu.iff.ccc.bsi.webdev.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.Reply;
import br.edu.iff.ccc.bsi.webdev.entities.Report;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.exception.ReportNotFoundException;
import br.edu.iff.ccc.bsi.webdev.repository.ReportRepository;

@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepo;

	
	public Report findById(Long id){
		Report post = reportRepo.findById(id).orElseThrow(() -> new ReportNotFoundException(id));
		return post;
	}
	
	public Report createReportPost(UserComum reporter,Post post, String reason) {
		
		Report report = new Report(reporter, post, reason);
	        return reportRepo.save(report);
	}
	
	public void createReportComment(UserComum reporter, Comment comment, String reason, Post post) {
        Report report = new Report(reporter, comment, reason, post);
        reportRepo.save(report);
    }

    public void createReportReply(UserComum reporter, Reply reply, String reason, Comment comment, Post post) {
        Report report = new Report(reporter, reply, reason, comment, post);
        reportRepo.save(report);
    }
	
	public Report save(Report post) {
        return reportRepo.save(post);
    }
	
	public List<Report> findAll() {
        return reportRepo.findAll();
    }
	
	public Report update(Long id, Report reportDetails) {
		if (!reportRepo.existsById(id)) {
			throw new ReportNotFoundException(id);
		}
        Report report = findById(id);
        report.setReason(reportDetails.getReason());
        return reportRepo.save(report);
    }

	public void deleteById(Long id) {
        if (!reportRepo.existsById(id)) {
            throw new ReportNotFoundException(id);
        }
        reportRepo.deleteById(id);
    }
}
