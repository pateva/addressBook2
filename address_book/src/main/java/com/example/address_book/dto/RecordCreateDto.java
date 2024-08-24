package com.example.address_book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordCreateDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String firmName;
    private AddressCreateDto address;
    private Set<ContactDetailCreateDto> contactDetails;
    @JsonProperty("isPersonal")
    private boolean isPersonal;
}
