package com.example.address_book.utils;

public interface IntegerValueEnum<E extends Enum<E>> {
    int getValue();
    E fromValue(int value);
}
