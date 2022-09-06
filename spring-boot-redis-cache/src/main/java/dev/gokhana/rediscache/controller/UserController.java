package dev.gokhana.rediscache.controller;

import dev.gokhana.rediscache.model.User;
import dev.gokhana.rediscache.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(description = "User related operations", name = "User")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        Long userId = Long.valueOf(id);
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        User updated = userService.updateUser(user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") String id) {
        Long userId = Long.valueOf(id);
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}

