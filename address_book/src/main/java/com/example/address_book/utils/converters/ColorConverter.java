package com.example.address_book.utils.converters;

import com.example.address_book.utils.Color;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ColorConverter extends IntegerValueEnumConverter<Color> {
    public ColorConverter() {
        super(Color.class);
    }
}
