package com.example.address_book.utils.converters;

import com.example.address_book.utils.IntegerValueEnum;
import jakarta.persistence.AttributeConverter;

public class IntegerValueEnumConverter<E extends Enum<E> & IntegerValueEnum<E>> implements AttributeConverter<E, Integer> {
    private final Class<E> enumType;
    public IntegerValueEnumConverter(Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Integer convertToDatabaseColumn(E enumValue) {
        if(enumValue == null) {
            return null;
        }

        return enumValue.getValue();
    }

    @Override
    public E convertToEntityAttribute(Integer dbValue) {
        if(dbValue == null) {
            return null;
        }

        return enumType.getEnumConstants()[0].fromValue(dbValue);
    }

}
