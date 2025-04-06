package br.edu.iff.ccc.bsi.webdev.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.controller.UserComumController;
import br.edu.iff.ccc.bsi.webdev.entities.UserComum;

@Component
public class UserComumDTO implements RepresentationModelAssembler<UserComum, EntityModel<UserComum>>{

	@Override
	public EntityModel<UserComum> toModel(UserComum entity) {
		return EntityModel.of(entity, linkTo(methodOn(UserComumController.class).getUser(entity.getId())).withSelfRel(),
				linkTo(methodOn(UserComumController.class).getAllUsers()).withRel("usuarios"));
	}
}
