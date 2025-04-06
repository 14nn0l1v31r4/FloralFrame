package br.edu.iff.ccc.bsi.webdev.dto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.entities.Comment;
import br.edu.iff.ccc.bsi.webdev.controller.CommentController;

@Component
public class CommentDTO implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {

	@Override
	public EntityModel<Comment> toModel(Comment entity) {
		return EntityModel.of(entity, linkTo(methodOn(CommentController.class).getComment(entity.getId())).withSelfRel(),
				linkTo(methodOn(CommentController.class).getAllComments()).withRel("comments"));
	}
}
