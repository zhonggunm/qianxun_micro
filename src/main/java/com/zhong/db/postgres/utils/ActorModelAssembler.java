package com.zhong.db.postgres.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.zhong.db.postgres.model.Actor;
import com.zhong.db.postgres.resources.ActorController;


@Component
public class ActorModelAssembler implements RepresentationModelAssembler<Actor, EntityModel<Actor>> {
	@Override
	public EntityModel<Actor> toModel(Actor Actor) {
		return new EntityModel<>(Actor,
				  	linkTo(methodOn(ActorController.class).one(Actor.getId())).withSelfRel(),
				  	linkTo(methodOn(ActorController.class).all()).withRel("Actors")); 
	}

}
