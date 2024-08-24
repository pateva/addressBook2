package com.example.address_book.controller;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.service.contract.ContactDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/contact-details")
@RequiredArgsConstructor
public class ContactDetailController {
    private final ContactDetailService contactDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<ContactDetailDto> getContactDetail(@PathVariable final Long id) {

        return ResponseEntity.ok(contactDetailService.getContactDetail(id));
    }

    @GetMapping("/record/{recordId}")
    public ResponseEntity<Set<ContactDetailDto>> getContactDetailsByRecord(@PathVariable final Long recordId) {

        return ResponseEntity.ok(contactDetailService.getContactDetailsByRecordId(recordId));
    }

    @PostMapping
    public ResponseEntity<ContactDetailDto> createContactDetail(@RequestBody final ContactDetailCreateDto contactDetailCreateDto) {

        return ResponseEntity.ok(contactDetailService.createContactDetail(contactDetailCreateDto));
    }
}
