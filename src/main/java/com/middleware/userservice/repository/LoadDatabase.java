package com.middleware.userservice.repository;
import com.middleware.userservice.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

/**
 * Loads DB with JPA
 */
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  /**
   * Initialises repository with a test user.
   * @param repository
   * @return
   */
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new User("kaikoh@test.com", "testtest", "Kai", "Koh")));
    };
  }
}