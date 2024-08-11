package com.example.address_book.utils;

import com.example.address_book.exceptions.ExceptionConstants;
import com.example.address_book.exceptions.InvalidEnumValueException;
import com.example.address_book.utils.converter.ConverterContract;
import lombok.Getter;

@Getter
public enum Color implements ConverterContract<Color> {
    RED(1),
    BLUE(2),
    GREEN(3),
    VELVET(4),
    YELLOW(5),
    ORANGE(6),
    PURPLE(7),
    PINK(8);

    private final int value;

    Color(int val) {
        this.value = val;
    }

    @Override
    public Color fromValue(Integer value) {
        for(Color type : Color.values()) {
            if(type.getValue() == value) {
                return type;
            }
        }

        throw new InvalidEnumValueException(String.format(ExceptionConstants.invalidEnumValue, "ContactType", value));
    }

    @Override
    public Integer getValue(Color enumValue) {
        return enumValue.getValue();
    }
}
