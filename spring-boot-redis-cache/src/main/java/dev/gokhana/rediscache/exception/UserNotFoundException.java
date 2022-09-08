package dev.gokhana.rediscache.exception;

// create a user not found exception extends RuntimeException
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}