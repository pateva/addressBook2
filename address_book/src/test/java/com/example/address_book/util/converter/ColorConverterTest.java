package com.example.address_book.util.converter;

import com.example.address_book.exception.InvalidEnumValueException;
import com.example.address_book.util.Color;
import org.junit.jupiter.api.Test;;

import static org.junit.jupiter.api.Assertions.*;

class ColorConverterTest {
    private final ColorConverter colorConverter = new ColorConverter();

    @Test
    void convertToDatabaseColumn_whenExisting_shouldReturnValue() {
        //arrange
        final Integer expected = 2;
        final Color color = Color.BLUE;

        //act
        var result = colorConverter.convertToDatabaseColumn(color);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void convertToEntityAttribute_whenExisting_shouldReturnValue() {
        //arrange
        final Color expected = Color.GREEN;
        final Integer integer = 3;

        //act
        var result = colorConverter.convertToEntityAttribute(integer);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void convertToEntityAttribute_whenNotExisting_throwsException() {
        //arrange
        final Integer integer = 51;

        //act & assert
        assertThrows(InvalidEnumValueException.class, () -> colorConverter.convertToEntityAttribute(integer));
    }
}