package com.middleware.userservice.exceptions;

public class UserNotDeletedException extends RuntimeException {

  public UserNotDeletedException(String email, String message) {
    super(String.format("Could not delete user %s - %s", email, message));
  }
}