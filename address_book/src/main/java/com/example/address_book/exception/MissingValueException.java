package com.example.address_book.exception;

public class MissingValueException extends RuntimeException{
    public MissingValueException(String msg) {
        super(msg);
    }
}
