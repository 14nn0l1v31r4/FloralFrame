package br.edu.iff.ccc.bsi.webdev.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.controller.ReplyController;
import br.edu.iff.ccc.bsi.webdev.entities.Reply;

@Component
public class ReplyDTO implements RepresentationModelAssembler<Reply, EntityModel<Reply>> {

    @Override
    public EntityModel<Reply> toModel(Reply reply) {
        return EntityModel.of(reply,
                linkTo(methodOn(ReplyController.class).getReply(reply.getId())).withSelfRel(),
                linkTo(methodOn(ReplyController.class).getAllReplies()).withRel("replies"));
    }
}
