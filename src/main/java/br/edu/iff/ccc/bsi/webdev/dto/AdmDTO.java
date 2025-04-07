package br.edu.iff.ccc.bsi.webdev.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.entities.Adm;
import br.edu.iff.ccc.bsi.webdev.controller.AdmController;

@Component
public class AdmDTO implements RepresentationModelAssembler<Adm, EntityModel<Adm>> {

	@Override
	public EntityModel<Adm> toModel(Adm entity) {
		return EntityModel.of(entity, linkTo(methodOn(AdmController.class).getAdm(entity.getId())).withSelfRel(),
				linkTo(methodOn(AdmController.class).getAllAdms()).withRel("adms"));
	}	

}
