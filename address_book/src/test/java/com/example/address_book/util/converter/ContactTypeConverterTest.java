package com.example.address_book.util.converter;

import com.example.address_book.exception.InvalidEnumValueException;
import com.example.address_book.util.ContactType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTypeConverterTest {
    private final ContactTypeConverter contactTypeConverter = new ContactTypeConverter();

    @Test
    void convertToDatabaseColumn_whenExisting_shouldReturnValue() {
        //arrange
        final Integer expected = 3;
        final ContactType contactType = ContactType.FAX;

        //act
        var result = contactTypeConverter.convertToDatabaseColumn(contactType);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void convertToEntityAttribute_whenExisting_shouldReturnValue() {
        //arrange
        final ContactType expected = ContactType.EMAIL;
        final Integer integer = 1;

        //act
        var result = contactTypeConverter.convertToEntityAttribute(integer);

        //assert
        assertEquals(expected, result);
    }

    @Test
    void convertToEntityAttribute_whenNotExisting_throwsException() {
        //arrange
        final Integer integer = 51;

        //act & assert
        assertThrows(InvalidEnumValueException.class, () -> contactTypeConverter.convertToEntityAttribute(integer));
    }
}