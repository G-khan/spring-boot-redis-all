package dev.gokhana.rediscache.service;

import dev.gokhana.rediscache.model.User;

import java.util.List;

public interface UserService {

    User getUserById(String id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
