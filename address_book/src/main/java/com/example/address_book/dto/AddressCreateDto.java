package com.example.address_book.dto;

import lombok.Data;

@Data
public class AddressCreateDto {
    private String street;
    private String city;
    private String country;
    private Long recordId;
}
