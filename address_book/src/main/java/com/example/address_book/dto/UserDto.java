package com.example.address_book.dto;

import lombok.Data;
import microsoft.sql.DateTimeOffset;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String email;
    private RecordDto personalRecord;
    private Set<LabelDto> labels;
    private Set<RecordDto> records;
    private DateTimeOffset createdAt;
    private DateTimeOffset updatedAt;
}
