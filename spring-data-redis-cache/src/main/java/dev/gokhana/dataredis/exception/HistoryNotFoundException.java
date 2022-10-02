package dev.gokhana.dataredis.exception;

// create a user not found exception extends RuntimeException
public class HistoryNotFoundException extends RuntimeException {
    public HistoryNotFoundException(String message) {
        super(message);
    }
}