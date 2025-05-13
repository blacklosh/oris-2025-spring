package ru.itis.springboottest.exceptions;

public class IncorrectPrice extends RuntimeException {
    public IncorrectPrice(String message) {
        super(message);
    }
}
