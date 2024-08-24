package com.example.address_book.controller;

import com.example.address_book.dto.AddressCreateDto;
import com.example.address_book.dto.AddressDto;
import com.example.address_book.service.contract.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable final Long addressId) {
        return ResponseEntity.ok(addressService.getAddress(addressId));
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody final AddressCreateDto addressDto) {
        return ResponseEntity.ok(addressService.createAddress(addressDto));
    }

    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@RequestBody final AddressDto addressDto) {
        return ResponseEntity.ok(addressService.updateAddress(addressDto));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Long addressId) {
        addressService.deleteAddress(addressId);

        return ResponseEntity.accepted().build();
    }
}
