package com.middleware.userservice.exceptions;

public class UserNotUpdatedException extends RuntimeException {

  public UserNotUpdatedException(String email) {
    super("Could not update user " + email);
  }
}