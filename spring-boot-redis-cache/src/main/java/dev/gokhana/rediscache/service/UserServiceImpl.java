package dev.gokhana.rediscache.service;

import dev.gokhana.rediscache.model.User;
import dev.gokhana.rediscache.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    // inject User repository
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @CacheEvict(cacheNames = "users", allEntries = true)
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
