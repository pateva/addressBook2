package com.example.address_book.dto;

import com.example.address_book.model.Address;
import lombok.Data;

import java.util.Set;

@Data
public class RecordDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String firmName;
    private Address address;
    private Set<ContactDetailDto> contactDetails;
    private Set<NoteDto> notes;
}
