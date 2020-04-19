package com.zhong.db.postgres.utils;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.zhong.db.postgres.model.Account;
import com.zhong.db.postgres.resources.AccountController;


@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>> {
	@Override
	public EntityModel<Account> toModel(Account Account) {
		return new EntityModel<>(Account,
				  	linkTo(methodOn(AccountController.class).one(Account.getId())).withSelfRel(),
				  	linkTo(methodOn(AccountController.class).all()).withRel("Accounts")); 
	}
}
