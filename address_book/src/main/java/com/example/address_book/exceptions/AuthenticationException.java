package com.example.address_book.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}
