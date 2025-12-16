package com.library.management.exception;

public class RenewingLimitExceededException extends RuntimeException {
    public RenewingLimitExceededException(String message) {
        super(message);
    }
}
