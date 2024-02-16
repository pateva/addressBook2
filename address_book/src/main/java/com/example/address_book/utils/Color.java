package com.example.address_book.utils;

import com.example.address_book.exceptions.ExceptionConstants;
import com.example.address_book.exceptions.InvalidEnumValueException;
import lombok.Getter;

@Getter
public enum Color implements IntegerValueEnum<Color> {
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
    public Color fromValue(int value) {
        for(Color color:Color.values()) {
            if(color.getValue() == value) {
                return color;
            }
        }
        throw new InvalidEnumValueException(String.format(ExceptionConstants.invalidEnumValue, "Color", value));
    }
}
