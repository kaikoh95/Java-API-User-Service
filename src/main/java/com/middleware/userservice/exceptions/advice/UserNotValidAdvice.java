package com.middleware.userservice.exceptions.advice;

import com.middleware.userservice.exceptions.UserNotValidException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserNotValidAdvice {

  @ResponseBody
  @ExceptionHandler(UserNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String userNotValidHandler(UserNotValidException ex) {
    return ex.getMessage();
  }
}