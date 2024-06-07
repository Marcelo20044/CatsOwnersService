package com.catService.contract.exceptions.cat;

public class CatNotExistException extends RuntimeException {
    public CatNotExistException() {
    }

    public CatNotExistException(String message) {
        super(message);
    }
}
