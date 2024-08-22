package com.example.address_book.exception;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
}
