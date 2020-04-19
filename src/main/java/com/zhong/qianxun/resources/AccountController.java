package com.zhong.qianxun.resources;


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

import com.zhong.qianxun.exceptions.AccountNotFoundException;
import com.zhong.qianxun.model.Account;
import com.zhong.qianxun.repository.AccountRepository;
import com.zhong.qianxun.utils.AccountModelAssembler;

@RestController
public class AccountController {

  @Autowired
  private final AccountRepository repository;
  
  @Autowired
  private final AccountModelAssembler assembler;

  
  public AccountController(AccountRepository repository, AccountModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root

  @GetMapping("/accounts")
  public CollectionModel<EntityModel<Account>> all() {
	  List<EntityModel<Account>> accounts = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
	  
	  return new CollectionModel<>(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
  }
  
  @GetMapping("/accounts/query")
  public CollectionModel<EntityModel<Account>> findByLastName(@RequestParam("lastName") String lastName) {
	  List<EntityModel<Account>> accounts = repository.findByLastName(lastName).stream().map(assembler::toModel).collect(Collectors.toList());
	  
	  return new CollectionModel<>(accounts, linkTo(methodOn(AccountController.class).all()).withSelfRel());
  }


  @PostMapping("/accounts")
  public ResponseEntity<?> newActor(@RequestBody Account newAccount) {
	  EntityModel<Account> entityModel = assembler.toModel(repository.save(newAccount));
	  
    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
  }

  // Single item

  @GetMapping("/accounts/{id}")
  public EntityModel<Account> one(@PathVariable Long id) {
	  Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
	  	
	  return assembler.toModel(account);  
  }

  @PutMapping("/accounts/{id}")
  public Account replaceActor(@RequestBody Account newAccount, @PathVariable Long id) {

    return repository.findById(id)
      .map(account -> {
        account.setFirstName(newAccount.getFirstName());
        account.setLastName(newAccount.getLastName());
        return repository.save(account);
      })
      .orElseGet(() -> {
        newAccount.setId(id);
        return repository.save(newAccount);
      });
  }

  @DeleteMapping("/accounts/{id}")
  public void deleteAccount(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
