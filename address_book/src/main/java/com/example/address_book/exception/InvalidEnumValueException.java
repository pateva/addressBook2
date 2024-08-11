package com.example.address_book.exception;

public class InvalidEnumValueException extends RuntimeException{
    public InvalidEnumValueException(String msg) {
        super(msg);
    }
}
