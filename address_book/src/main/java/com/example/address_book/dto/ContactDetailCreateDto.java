package com.example.address_book.dto;

import com.example.address_book.util.ContactType;
import lombok.Data;

@Data
public class ContactDetailCreateDto {
    private Long recordId;
    private ContactType type;
    private String value;
}
