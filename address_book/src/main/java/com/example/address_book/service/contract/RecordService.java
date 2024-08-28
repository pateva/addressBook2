package com.example.address_book.service.contract;

import com.example.address_book.dto.PagedRecordDto;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordImageDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.model.Address;
import org.springframework.data.domain.Pageable;

public interface RecordService {
    RecordDto createRecord(RecordCreateDto recordCreateDto);
    PagedRecordDto getPagedRecordsByUserId(Long userId, Pageable pageable);
    PagedRecordDto getPagedRecordsByUserIdAndLabel(Long userId, Long labelId, Pageable pageable);
    RecordDto getRecordById(Long recordId);
    RecordDto updateRecord(Long id, RecordUpdateDto recordUpdateDto);
    boolean existsById(Long id);
    void deleteRecord(Long id);
    void updateAddress(Long id, Address address);
    void removeAddress(Long addressId);
    void removeContactDetail(Long id, Long contactDetailId);
    void addLabelToRecord(Long id, Long labelId);
    void removeLabelFromRecord(Long id, Long labelId);
    RecordDto getPersonalRecord(Long userId);
    RecordDto addImageToRecord(Long id, RecordImageDto imageDtp);
}
