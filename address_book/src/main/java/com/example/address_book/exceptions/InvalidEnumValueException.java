package com.example.address_book.exceptions;

public class InvalidEnumValueException extends RuntimeException{
    public InvalidEnumValueException(String msg) {
        super(msg);
    }
}
