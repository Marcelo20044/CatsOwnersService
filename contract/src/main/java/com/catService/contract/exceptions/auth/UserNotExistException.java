package com.catService.contract.exceptions.auth;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String message) {
        super(message);
    }

    public UserNotExistException() {
    }
}
