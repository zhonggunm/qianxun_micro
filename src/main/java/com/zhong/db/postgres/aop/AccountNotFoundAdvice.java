package com.zhong.db.postgres.aop;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zhong.db.postgres.exceptions.AccountNotFoundException;

@ControllerAdvice
public class AccountNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(AccountNotFoundException ex) {
    return ex.getMessage();
  }
}