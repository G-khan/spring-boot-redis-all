package dev.gokhana.rediscache.service;

import dev.gokhana.rediscache.model.User;

public interface UserService {

    User getUserById(String id);

    User createUser(User user);

    User updateUser(String id, User user);

    void deleteUser(Long id);
}
