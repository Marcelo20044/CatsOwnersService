package com.catService.contract.exceptions.cat;

public class CatAndFriendSameException extends RuntimeException {
    public CatAndFriendSameException() {
        super();
    }

    public CatAndFriendSameException(String message) {
        super(message);
    }
}
