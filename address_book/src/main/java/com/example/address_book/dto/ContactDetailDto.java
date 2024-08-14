package com.example.address_book.dto;

import com.example.address_book.util.ContactType;
import lombok.Data;

@Data
public class ContactDetailDto {
    private Long id;
    private ContactType type;
    private String value;
}
