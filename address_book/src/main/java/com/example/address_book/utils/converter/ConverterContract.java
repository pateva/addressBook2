package com.example.address_book.utils.converter;

public interface ConverterContract<E extends Enum<E>> {
    E fromValue(Integer value);
    Integer getValue(E enumValue);
}
