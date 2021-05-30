package com.middleware.userservice.controllers;

import com.middleware.userservice.exceptions.UserNotCreatedException;
import com.middleware.userservice.exceptions.UserNotDeletedException;
import com.middleware.userservice.exceptions.UserNotFoundException;
import com.middleware.userservice.exceptions.UserNotUpdatedException;
import com.middleware.userservice.exceptions.UserNotValidException;
import com.middleware.userservice.models.User;
import com.middleware.userservice.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for User REST services
 */
@RestController
class UserController {

  private final UserRepository repository;
  private volatile String message;

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String createUser(@RequestBody User newUser) {
    message = "";
    if (!newUser.containsRequired()) {
      throw new UserNotCreatedException(newUser.getEmail(), "missing required fields");
    }
    repository.findById(newUser.getEmail())
    .map(user -> {
      throw new UserNotCreatedException(newUser.getEmail(), "user already exists");
    });
    try {
      message = "The user was created successfully";
      repository.save(newUser); 
    } catch (Exception e) {
      throw new UserNotCreatedException(newUser.getEmail(), "unable to save user");
    }
    return message;
  }

  @GetMapping("/user/{email}")
  User getUser(@PathVariable String email) {
    User user = repository.findById(email)
      .orElseThrow(() -> new UserNotFoundException(email));
    return user;
  }

  @PutMapping("/user/{email}")
  String updateAUser(@RequestBody User newUser, @PathVariable String email) {
    if (!newUser.containsRequired()) {
      throw new UserNotUpdatedException(email, "user is not valid");
    }
    message = "";
    repository.findById(email)
    .map(user -> {
      if (user.validateUser(newUser)) {
        try {
          repository.deleteById(email);
          user.updateUser(newUser);
          repository.save(user);
          message = "The user was updated successfully";
        } catch (Exception e) {
          throw new UserNotUpdatedException(user.getEmail(), "unable to update user");
        }
        return user;
      }
      throw new UserNotUpdatedException(email, "user is not valid");
    })
    .orElseThrow(() -> new UserNotUpdatedException(email, "user does not exist"));
    return message;
  }

  @DeleteMapping("/user/{email}")
  String deleteUser(@PathVariable String email) {
    message = "";
    repository.findById(email)
    .map(user -> {
      return user;
    })
    .orElseThrow(() -> new UserNotDeletedException(email, "user does not exist"));
    try {
      repository.deleteById(email);
      message = "The user was deleted successfully";
    } catch (Exception e) {
      throw new UserNotDeletedException(email, "something went wrong when deleting user");
    }
    return message;
  }
}