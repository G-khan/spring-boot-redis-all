package dev.gokhana.rediscache.repository;

import dev.gokhana.rediscache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// create a user repository extends JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {

}
