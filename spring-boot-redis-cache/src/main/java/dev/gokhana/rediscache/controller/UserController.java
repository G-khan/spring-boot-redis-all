package dev.gokhana.rediscache.controller;

import dev.gokhana.rediscache.model.User;
import dev.gokhana.rediscache.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(description = "User related operations", name = "User")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") String id) {
        log.info("get user with id {}", id);
        User user = userService.getUserById(id);
        log.info("user found: {}", user);
        return user;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User created = userService.createUser(user);
        log.info("User is created: {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        log.info("User is updated {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") String id) {
        Long userId = Long.valueOf(id);
        log.info("delete user with id {}", id);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

