package com.example.address_book.service.contract;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(RecordCreateDto recordCreateDto);
    List<RecordPartialDto> getRecordsByUserId(Long userId);
    RecordDto getRecordById(Long recordId);
    RecordDto updateRecord(RecordUpdateDto recordUpdateDto);
    boolean existsById(Long id);
}
