package com.example.address_book.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String country;
}
