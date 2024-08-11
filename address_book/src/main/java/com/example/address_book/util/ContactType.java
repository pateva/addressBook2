package com.example.address_book.util;

import lombok.Getter;

@Getter
public enum ContactType {
    EMAIL(1),
    PHONE_NUMBER(2),
    FAX(3);

    private final int value;

    ContactType(int val) {
        this.value = val;
    }
}
