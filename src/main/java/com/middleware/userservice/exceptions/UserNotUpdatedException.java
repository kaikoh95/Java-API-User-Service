package com.middleware.userservice.exceptions;

public class UserNotUpdatedException extends RuntimeException {

  public UserNotUpdatedException(String email, String message) {
    super(String.format("Could not update user %s - %s", email, message));
  }
}