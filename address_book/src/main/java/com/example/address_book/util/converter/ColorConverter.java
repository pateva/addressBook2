package com.example.address_book.util.converter;

import com.example.address_book.exception.ExceptionConstants;
import com.example.address_book.exception.InvalidEnumValueException;
import com.example.address_book.util.Color;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<Color, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Color color) {
        return color.getValue();
    }

    @Override
    public Color convertToEntityAttribute(Integer integer) {
        for(Color type : Color.values()) {
            if(type.getValue() == integer) {
                return type;
            }
        }

        throw new InvalidEnumValueException(String.format(ExceptionConstants.invalidEnumValue, "Color", integer));
    }
}
