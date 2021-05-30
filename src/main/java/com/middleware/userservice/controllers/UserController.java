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

  UserController(UserRepository repository) {
    this.repository = repository;
  }

  @PostMapping("/user")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  String createUser(@RequestBody User newUser) {
    if (!newUser.containsRequired()) {
      throw new UserNotValidException(newUser.getEmail());
    }
    try {
      repository.save(newUser);
      return "The user was created successfully";
    } catch (Exception e) {
      throw new UserNotCreatedException(newUser.getEmail());
    }
  }

  @GetMapping("/user/{email}")
  String getUser(@PathVariable String email) {
    
    User user = repository.findById(email)
      .orElseThrow(() -> new UserNotFoundException(email));
    return user.toString();
  }

  @PutMapping("/user/{email}")
  String updateAUser(@RequestBody User newUser, @PathVariable String email) {
    if (!newUser.containsRequired()) {
      throw new UserNotValidException(email);
    }
    repository.findById(email)
      .map(user -> {
        if (user.validateUser(newUser)) {
          user.updateUser(newUser);
          user.setEmail(email);
          try {
            repository.save(user);
          } catch (Exception e) {
            throw new UserNotUpdatedException(user.getEmail());
          }
        }
        throw new UserNotValidException(email);
      });
      return "The user was updated successfully";
  }

  @DeleteMapping("/user/{email}")
  String deleteUser(@PathVariable String email) {
    try {
      repository.deleteById(email);
    } catch (Exception e) {
      throw new UserNotDeletedException(email);
    }
    return "The user was deleted successfully";
  }
}