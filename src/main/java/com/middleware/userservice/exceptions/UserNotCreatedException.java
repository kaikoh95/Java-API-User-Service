package com.middleware.userservice.exceptions;

public class UserNotCreatedException extends RuntimeException {

  public UserNotCreatedException(String email) {
    super("Could not create user " + email);
  }
}