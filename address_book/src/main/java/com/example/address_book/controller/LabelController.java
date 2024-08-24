package com.example.address_book.controller;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;
import com.example.address_book.service.contract.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping
    public ResponseEntity<LabelDto> createLabel(@RequestBody final LabelCreateDto labelDto) {

        return ResponseEntity.ok(labelService.createLabel(labelDto));
    }

}
