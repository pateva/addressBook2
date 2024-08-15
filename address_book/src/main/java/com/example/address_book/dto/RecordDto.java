package com.example.address_book.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
public class RecordDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String firmName;
    private AddressDto address;
    private Set<ContactDetailDto> contactDetails;
    private Set<NoteDto> notes;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
