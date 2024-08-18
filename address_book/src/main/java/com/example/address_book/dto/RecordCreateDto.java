package com.example.address_book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordCreateDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String firmName;
    private AddressDto address;
    private Set<ContactDetailDto> contactDetails;
}