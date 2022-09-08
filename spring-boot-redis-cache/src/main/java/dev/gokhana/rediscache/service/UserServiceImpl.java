package dev.gokhana.rediscache.service;

import dev.gokhana.rediscache.exception.UserNotFoundException;
import dev.gokhana.rediscache.model.User;
import dev.gokhana.rediscache.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service
public class UserServiceImpl implements UserService {

    // inject User repository
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // create a logger
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(cacheNames = "user", key = "#id")
    @Override
    public User getUserById(String id) {
        Long userId = Long.valueOf(id);
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }
    @Override
    public User createUser(User user) {
        var createdUser = userRepository.save(user);
        cacheUser(createdUser);
        return createdUser;
    }

    @Cacheable(value = "user", key = "#createdUser.id")
    public void cacheUser(User createdUser) { // dummy code for example
        logger.debug("Caching user with id {}", createdUser.getId());
    }

    @CachePut(cacheNames = "user", key = "#user.id")
    @Override
    public User updateUser(User user) {
        return userRepository.findById(user.getId())
                .map(u -> {
                    u.setName(user.getName());
                    u.setSurname(user.getSurname());
                    u.setAge(user.getAge());
                    return userRepository.save(u);
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + user.getId()));
    }
    @CacheEvict(cacheNames = "user", key = "#id")
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
