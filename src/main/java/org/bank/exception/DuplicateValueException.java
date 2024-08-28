package org.bank.exception;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException(final String message) {
        super(message);
    }
}
