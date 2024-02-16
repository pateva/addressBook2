package com.example.address_book.utils.converters;

import com.example.address_book.utils.ContactType;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ContactTypeConverter extends IntegerValueEnumConverter<ContactType> {
    public ContactTypeConverter(){
        super(ContactType.class);
    }
}
