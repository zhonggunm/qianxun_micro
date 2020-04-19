package com.zhong.db.postgres.exceptions;

public class AccountNotFoundException extends RuntimeException {

	  public AccountNotFoundException(Long id) {
	    super("Could not find Actor " + id);
	  }
	}