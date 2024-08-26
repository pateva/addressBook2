package com.example.address_book.dto;

import lombok.Data;

import java.util.List;

@Data
public class PagedRecordDto {
    private int totalPages;
    private long totalElements;
    private List<RecordPartialDto> recordDtoList;
}
