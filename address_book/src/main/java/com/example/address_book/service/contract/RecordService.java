package com.example.address_book.service.contract;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(RecordCreateDto recordCreateDto);
    List<RecordPartialDto> getRecordsByUserId(Long userId);
}
