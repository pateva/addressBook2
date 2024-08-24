package com.example.address_book.service.contract;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.model.Address;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(RecordCreateDto recordCreateDto);
    List<RecordPartialDto> getRecordsByUserId(Long userId);
    RecordDto getRecordById(Long recordId);
    RecordDto updateRecord(Long id, RecordUpdateDto recordUpdateDto);
    boolean existsById(Long id);
    void deleteRecord(Long id);
    void updateAddress(Long id, Address address);
    void removeAddress(Long addressId);
}
