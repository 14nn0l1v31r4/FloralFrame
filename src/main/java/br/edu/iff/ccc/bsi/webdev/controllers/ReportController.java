package br.edu.iff.ccc.bsi.webdev.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.Report;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.ReportService;

@RestController
@RequestMapping("api/v1/report")
public class ReportController {
	
	@Autowired
    private ReportService reportService;
	
    // Endpoint para buscar um post por ID
    @GetMapping("/{id}")
    public Report getReply(@PathVariable Long id) {
        return reportService.findById(id);
    }

    @PostMapping("/create")
    public Report createReply(@RequestParam UserComum reporter, 
                           @RequestParam Post post, 
                           @RequestParam String reason
                           )
                           {
        return reportService.createReport(reporter, post, reason );
    }
    
    @GetMapping("/all")
    public List<Report> getAllReports() {
        return reportService.findAll();
    }

    // Endpoint para atualizar um relatório
    @PutMapping("/update/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        return reportService.update(id, reportDetails);
    }

    // Endpoint para excluir um relatório
    @DeleteMapping("/delete/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteById(id);
    }

}
