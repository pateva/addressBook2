package com.example.address_book.controller;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.service.contract.ContactDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact-details")
@RequiredArgsConstructor
public class ContactDetailController {
    private final ContactDetailService contactDetailService;

    @PostMapping
    public ResponseEntity<ContactDetailDto> createContactDetail(@RequestBody final ContactDetailCreateDto contactDetailCreateDto) {

        return ResponseEntity.ok(contactDetailService.createContactDetail(contactDetailCreateDto));
    }
}
