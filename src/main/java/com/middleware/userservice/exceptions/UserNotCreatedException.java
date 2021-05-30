package com.middleware.userservice.exceptions;

public class UserNotCreatedException extends RuntimeException {

  public UserNotCreatedException(String email, String message) {
    super(String.format("Could not create user %s - %s", email, message));
  }
}