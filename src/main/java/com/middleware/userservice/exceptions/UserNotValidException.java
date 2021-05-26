package com.middleware.userservice.exceptions;

public class UserNotValidException extends RuntimeException {

  public UserNotValidException(String email) {
    super("User provided is not valid" + email);
  }
}