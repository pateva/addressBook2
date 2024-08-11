package com.example.address_book.utils.converter;

import com.example.address_book.exceptions.ExceptionConstants;
import com.example.address_book.exceptions.InvalidEnumValueException;
import com.example.address_book.utils.ContactType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContactTypeConverter implements AttributeConverter<ContactType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ContactType contactType) {
        return contactType.getValue();
    }

    @Override
    public ContactType convertToEntityAttribute(Integer integer) {
        for(ContactType type : ContactType.values()) {
            if(type.getValue() == integer) {
                return type;
            }
        }

        throw new InvalidEnumValueException(String.format(ExceptionConstants.invalidEnumValue, "ContactType", integer));
    }
}
