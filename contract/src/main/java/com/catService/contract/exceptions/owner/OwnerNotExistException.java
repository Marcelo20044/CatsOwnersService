package com.catService.contract.exceptions.owner;

public class OwnerNotExistException extends RuntimeException {
    public OwnerNotExistException() {
    }

    public OwnerNotExistException(String message) {
        super(message);
    }
}
