package com.example.address_book.controller;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @GetMapping("/test")
    public ResponseEntity<String> getRecord(@AuthenticationPrincipal CustomUserPrincipal customUserPrincipal) {
//        System.err.println(SecurityContextHolder.getContext().getAuthentication().getName());
//        System.out.println(customUserPrincipal);

        return new ResponseEntity<>("Returned records", HttpStatus.OK);
    }

    //TODO ADD SORTING AND FILTERING BY LABEL
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecordPartialDto>> getRecords(@PathVariable final Long userId) {

        return ResponseEntity.ok(recordService.getRecordsByUserId(userId));
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<RecordDto> getRecordById(@PathVariable final Long recordId) {

        return ResponseEntity.ok(recordService.getRecordById(recordId));
    }

    @PostMapping
    public ResponseEntity<RecordDto> createRecord(@RequestBody final RecordCreateDto recordCreateDto) {

        return ResponseEntity.ok(recordService.createRecord(recordCreateDto));
    }

    @PatchMapping
    public ResponseEntity<RecordDto> updateRecord(@RequestBody final RecordUpdateDto recordDto) {

        return ResponseEntity.ok(recordService.updateRecord(recordDto));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable final Long recordId) {
        recordService.deleteRecord(recordId);

        return ResponseEntity.accepted().build();
    }
}
