package br.edu.iff.ccc.bsi.webdev.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.controller.CommunityController;
import br.edu.iff.ccc.bsi.webdev.entities.Community;

@Component
public class CommunityDTO implements RepresentationModelAssembler<Community, EntityModel<Community>> {

	@Override
	public EntityModel<Community> toModel(Community entity) {
		return EntityModel.of(entity,
				linkTo(methodOn(CommunityController.class).getCommunity(entity.getId())).withSelfRel(),
				linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("communities"));
	}

}
