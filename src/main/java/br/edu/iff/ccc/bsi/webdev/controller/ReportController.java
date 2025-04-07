package br.edu.iff.ccc.bsi.webdev.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.webdev.dto.ReportDTO;
import br.edu.iff.ccc.bsi.webdev.dto.ReportRequestDTO;
import br.edu.iff.ccc.bsi.webdev.entities.Post;
import br.edu.iff.ccc.bsi.webdev.entities.Report;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;
import br.edu.iff.ccc.bsi.webdev.services.PostService;
import br.edu.iff.ccc.bsi.webdev.services.ReportService;
import br.edu.iff.ccc.bsi.webdev.services.UserComumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/report")
@Tag(name = "Reports", description = "API para gerenciamento de denúncias de postagens")
public class ReportController {
	
	@Autowired
    private ReportService reportService;
	
	@Autowired
	private ReportDTO reportDTO;
	
	@Autowired
	private UserComumService UserComumService;
	
	@Autowired
	private PostService postService;

    @PostMapping("/create")
    @Operation(summary = "Criar um novo relatório de denúncia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Relatório criado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário ou Post não encontrado")
    })
    public EntityModel<Report> createReport( @RequestBody @Valid ReportRequestDTO dto) {
    	
    	UserComum user = UserComumService.findById(dto.getUserId());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
		}
        Post post = postService.findById(dto.getPostId());
		if (post == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post não encontrado");
		}

        Report report = new Report(user, post, dto.getReason());
        reportService.save(report);

        return reportDTO.toModel(report);
    }
    
    @GetMapping("/search/{id}")
    @Operation(summary = "Buscar relatório por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Relatório encontrado"),
        @ApiResponse(responseCode = "404", description = "Relatório não encontrado")
    })
    public EntityModel<Report> getReport(@PathVariable Long id) {
        Report report = reportService.findById(id);
		return reportDTO.toModel(report);
    }
    
    @GetMapping("/all")
    @Operation(summary = "Listar todos os relatórios")
    public CollectionModel<EntityModel<Report>> getAllReports() {
        List<EntityModel<Report>> reports = reportService.findAll().stream()
                .map(reportDTO::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(reports, linkTo(methodOn(ReportController.class).getAllReports()).withSelfRel());
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Atualizar um relatório existente")
    public EntityModel<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        Report updatedReport = reportService.update(id, reportDetails);
        return reportDTO.toModel(updatedReport);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Excluir um relatório")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteById(id);
    }

}
