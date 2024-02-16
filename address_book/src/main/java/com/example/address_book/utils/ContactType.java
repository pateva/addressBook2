package com.example.address_book.utils;

import com.example.address_book.exceptions.ExceptionConstants;
import com.example.address_book.exceptions.InvalidEnumValueException;
import lombok.Getter;

@Getter
public enum ContactType implements IntegerValueEnum<ContactType> {
    EMAIL(1),
    PHONE_NUMBER(2),
    FAX(3);

    private final int value;

    ContactType(int val) {
        this.value = val;
    }

    @Override
    public ContactType fromValue(int value) {
        for(ContactType type : ContactType.values()) {
            if(type.getValue() == value) {
                return type;
            }
        }

        throw new InvalidEnumValueException(String.format(ExceptionConstants.invalidEnumValue, "ContactType", value));
    }
}
