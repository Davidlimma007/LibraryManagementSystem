package com.library.management.exception;

public class OverdueLoanCannotBeRenewedException extends RuntimeException {
    public OverdueLoanCannotBeRenewedException(String message) {
        super(message);
    }
}
