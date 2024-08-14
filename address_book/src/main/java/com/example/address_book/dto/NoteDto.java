package com.example.address_book.dto;

import lombok.Data;
import microsoft.sql.DateTimeOffset;

@Data
public class NoteDto {
    private Long id;
    private String text;
    private DateTimeOffset createdAt;
    private DateTimeOffset updatedAt;
}
