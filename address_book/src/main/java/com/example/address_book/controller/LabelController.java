package com.example.address_book.controller;

import com.example.address_book.dto.LabelCreateDto;
import com.example.address_book.dto.LabelDto;
import com.example.address_book.service.contract.LabelService;
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
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @GetMapping("/{labelId}")
    public ResponseEntity<LabelDto> getLabel(@PathVariable final Long labelId) {

        return ResponseEntity.ok(labelService.getLabel(labelId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Set<LabelDto>> getLabelsPerUser(@PathVariable final Long userId) {

        return ResponseEntity.ok(labelService.getByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<LabelDto> createLabel(@RequestBody final LabelCreateDto labelDto) {

        return ResponseEntity.ok(labelService.createLabel(labelDto));
    }

}
