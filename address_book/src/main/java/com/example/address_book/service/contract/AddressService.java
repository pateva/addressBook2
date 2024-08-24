package com.example.address_book.service.contract;

import com.example.address_book.dto.AddressCreateDto;
import com.example.address_book.dto.AddressDto;

public interface AddressService {
    AddressDto createAddress(AddressCreateDto addressDto);
    AddressDto getAddress(Long id);
    void deleteAddress(Long id);
    AddressDto updateAddress(AddressDto addressDto);
}
