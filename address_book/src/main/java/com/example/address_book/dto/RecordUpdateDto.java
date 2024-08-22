package com.example.address_book.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RecordUpdateDto {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String firmName;
    private AddressDto address;
    private Set<ContactDetailDto> contactDetails;
}
