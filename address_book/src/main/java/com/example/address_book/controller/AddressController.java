package com.example.address_book.controller;

import com.example.address_book.dto.AddressCreateDto;
import com.example.address_book.dto.AddressDto;
import com.example.address_book.service.contract.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody final AddressCreateDto addressDto) {
        return ResponseEntity.ok(addressService.createAddress(addressDto));
    }
}
