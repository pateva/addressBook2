package com.example.address_book.service.contract;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;

public interface ContactDetailService {
    ContactDetailDto createContactDetail(ContactDetailCreateDto contactDetailCreateDto);
}
