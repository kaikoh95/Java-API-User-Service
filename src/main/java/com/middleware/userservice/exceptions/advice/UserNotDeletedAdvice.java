package com.middleware.userservice.exceptions.advice;

import com.middleware.userservice.exceptions.UserNotDeletedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotDeletedAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotDeletedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String userNotDeletedHandler(UserNotDeletedException ex) {
    return ex.getMessage();
  }
}