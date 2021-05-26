package com.middleware.userservice.exceptions.advice;

import com.middleware.userservice.exceptions.UserNotUpdatedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotUpdatedAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotUpdatedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String userNotUpdatedHandler(UserNotUpdatedException ex) {
    return ex.getMessage();
  }
}