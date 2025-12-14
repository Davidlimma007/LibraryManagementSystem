package com.library.management.exception;

public class BlackListedUserException extends RuntimeException {
    public BlackListedUserException(String message) {
        super(message);
    }
}
