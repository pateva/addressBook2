package com.example.address_book.utils.converter;

import com.example.address_book.exceptions.InvalidEnumValueException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenericConverter<E extends Enum<E> & ConverterContract<E>> implements AttributeConverter<E, Integer> {
    private final Class<E> enumClass;

    public GenericConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Integer convertToDatabaseColumn(E e) {
        if (e == null) {
            return null;
        }

        return e.getValue(e);
    }

    @Override
    public E convertToEntityAttribute(Integer integer) {
        if (integer == null) {
            return null;
        }

        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getValue(enumConstant).equals(integer)) {
                return enumConstant.fromValue(integer);
            }
        }

        throw new InvalidEnumValueException("Unknown database value: " + integer);
    }
}
