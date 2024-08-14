package com.example.address_book.dto;

import lombok.Data;
import microsoft.sql.DateTimeOffset;

import java.time.OffsetDateTime;

@Data
public class NoteDto {
    private Long id;
    private String text;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
