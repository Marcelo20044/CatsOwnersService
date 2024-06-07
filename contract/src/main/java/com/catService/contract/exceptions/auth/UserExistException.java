package com.catService.contract.exceptions.auth;

public class UserExistException extends RuntimeException {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
