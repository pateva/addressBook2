package com.example.address_book.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPartialDto {
    private Long id;
    private String email;
    private List<RecordDto> personalRecords;
}
