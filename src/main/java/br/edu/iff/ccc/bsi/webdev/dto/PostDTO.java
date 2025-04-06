package br.edu.iff.ccc.bsi.webdev.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.webdev.controller.PostController;
import br.edu.iff.ccc.bsi.webdev.entities.Post;

@Component
public class PostDTO  implements RepresentationModelAssembler<Post, EntityModel<Post>> {

    @Override
    public EntityModel<Post> toModel(Post post) {
        return EntityModel.of(post,
                linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel(),
                linkTo(methodOn(PostController.class).getAllPosts()).withRel("posts"));
    }
}