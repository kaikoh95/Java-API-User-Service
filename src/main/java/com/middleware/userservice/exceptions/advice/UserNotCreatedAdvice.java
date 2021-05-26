package com.middleware.userservice.exceptions.advice;

import com.middleware.userservice.exceptions.UserNotCreatedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotCreatedAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotCreatedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String userNotCreatedHandler(UserNotCreatedException ex) {
    return ex.getMessage();
  }
}