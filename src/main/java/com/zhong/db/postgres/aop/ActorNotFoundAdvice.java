package com.zhong.db.postgres.aop;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zhong.db.postgres.exceptions.ActorNotFoundException;

@ControllerAdvice
public class ActorNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ActorNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(ActorNotFoundException ex) {
    return ex.getMessage();
  }
}