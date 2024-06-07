package com.catService.contract.exceptions.auth;

public class InvalidAuthDataException extends RuntimeException{
    public InvalidAuthDataException() {
    }

    public InvalidAuthDataException(String message) {
        super(message);
    }
}
