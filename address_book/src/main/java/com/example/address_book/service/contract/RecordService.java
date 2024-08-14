package com.example.address_book.service.contract;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;

public interface RecordService {
    RecordDto createRecord(RecordCreateDto recordCreateDto);
}
