package com.example.address_book.service.contract;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;

import java.util.Set;

public interface ContactDetailService {
    ContactDetailDto createContactDetail(ContactDetailCreateDto contactDetailCreateDto);
    ContactDetailDto getContactDetail(Long id);
    Set<ContactDetailDto> getContactDetailsByRecordId(Long recordId);
    ContactDetailDto updateContactDetails(Long id, ContactDetailDto contactDetailDto);
}
