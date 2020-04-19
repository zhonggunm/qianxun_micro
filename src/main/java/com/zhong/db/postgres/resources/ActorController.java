package com.zhong.db.postgres.resources;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhong.db.postgres.exceptions.ActorNotFoundException;
import com.zhong.db.postgres.model.Actor;
import com.zhong.db.postgres.repository.ActorRepository;
import com.zhong.db.postgres.utils.ActorModelAssembler;

@RestController
public class ActorController {

  @Autowired
  private final ActorRepository repository;
  
  @Autowired
  private final ActorModelAssembler assembler;

  
  public ActorController(ActorRepository repository, ActorModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root

  @GetMapping("/actors")
  public CollectionModel<EntityModel<Actor>> all() {
	  List<EntityModel<Actor>> Actors = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
	  
	  return new CollectionModel<>(Actors, linkTo(methodOn(ActorController.class).all()).withSelfRel());
  }
  
  @GetMapping("/actors/query")
  public CollectionModel<EntityModel<Actor>> findByLastName(@RequestParam("lastName") String lastName) {
	  List<EntityModel<Actor>> Actors = repository.findByLastName(lastName).stream().map(assembler::toModel).collect(Collectors.toList());
	  
	  return new CollectionModel<>(Actors, linkTo(methodOn(ActorController.class).all()).withSelfRel());
  }


  @PostMapping("/actors")
  public ResponseEntity<?> newActor(@RequestBody Actor newActor) {
	  EntityModel<Actor> entityModel = assembler.toModel(repository.save(newActor));
	  
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  // Single item

  @GetMapping("/actors/{id}")
  public EntityModel<Actor> one(@PathVariable Long id) {
	  Actor Actor = repository.findById(id).orElseThrow(() -> new ActorNotFoundException(id));
	  	
	  return assembler.toModel(Actor);
	  
  }

  @PutMapping("/actors/{id}")
  
  
  public Actor replaceActor(@RequestBody Actor newActor, @PathVariable Long id) {

    return repository.findById(id)
      .map(Actor -> {
        Actor.setFirstName(newActor.getFirstName());
        Actor.setLastName(newActor.getLastName());
        return repository.save(Actor);
      })
      .orElseGet(() -> {
        newActor.setId(id);
        return repository.save(newActor);
      });
  }

  @DeleteMapping("/actors/{id}")
  public void deleteActor(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
