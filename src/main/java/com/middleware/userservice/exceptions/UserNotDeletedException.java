package com.middleware.userservice.exceptions;

public class UserNotDeletedException extends RuntimeException {

  public UserNotDeletedException(String email) {
    super("Could not delete user " + email);
  }
}