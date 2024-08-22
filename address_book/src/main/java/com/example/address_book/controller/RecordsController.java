package com.example.address_book.controller;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordsController {
    private final RecordService recordService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getRecord(@AuthenticationPrincipal CustomUserPrincipal customUserPrincipal) {
//        System.err.println(SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println(customUserPrincipal);

        return new ResponseEntity<>("Returned records", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RecordPartialDto>> getRecords(@PathVariable final Long userId) {

        return new ResponseEntity<>(recordService.getRecordsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecordDto> createRecord(@RequestBody final RecordCreateDto recordCreateDto) {

        return new ResponseEntity<>(recordService.createRecord(recordCreateDto), HttpStatus.OK);
    }

}
