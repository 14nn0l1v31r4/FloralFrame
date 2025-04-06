package br.edu.iff.ccc.bsi.webdev.dto;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.controller.ReportController;
import br.edu.iff.ccc.bsi.webdev.entities.Report;

@Component
public class ReportDTO implements RepresentationModelAssembler<Report, EntityModel<Report>> {

    @Override
    public EntityModel<Report> toModel(Report report) {
        return EntityModel.of(report,
                linkTo(methodOn(ReportController.class).getReport(report.getId())).withSelfRel(),
                linkTo(methodOn(ReportController.class).getAllReports()).withRel("reports"));
    }
}
