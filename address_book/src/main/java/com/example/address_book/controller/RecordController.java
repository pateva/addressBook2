package com.example.address_book.controller;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.dto.PagedRecordDto;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordImageDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/user/{userId}/records")
    public ResponseEntity<PagedRecordDto> getRecordsPaged(@PathVariable final Long userId, Pageable pageable) {

        return ResponseEntity.ok(recordService.getPagedRecordsByUserId(userId, pageable));
    }

    @GetMapping("/user/{userId}/personal")
    public ResponseEntity<RecordDto> getPersonalRecord(@PathVariable final Long userId) {

        return ResponseEntity.ok(recordService.getPersonalRecord(userId));
    }

    @GetMapping("/user/{userId}/label/{labelId}")
    public ResponseEntity<PagedRecordDto> getRecordsByLabelPaged(@PathVariable final Long userId, @PathVariable final Long labelId, Pageable pageable) {

        return ResponseEntity.ok(recordService.getPagedRecordsByUserIdAndLabel(userId, labelId, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordDto> getRecordById(@PathVariable final Long id) {

        return ResponseEntity.ok(recordService.getRecordById(id));
    }

    @PostMapping
    public ResponseEntity<RecordDto> createRecord(@RequestBody final RecordCreateDto recordCreateDto) {

        return ResponseEntity.ok(recordService.createRecord(recordCreateDto));
    }

    @PostMapping("/{id}/label/{labelId}")
    public ResponseEntity<Void> addRecordToLabel(@PathVariable final Long id, @PathVariable final Long labelId) {
        recordService.addLabelToRecord(id, labelId);

        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<RecordDto> addImageToRecord(@PathVariable final Long id, @RequestBody final RecordImageDto url) {

        return ResponseEntity.ok(recordService.addImageToRecord(id, url));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable final Long id, @RequestBody final RecordUpdateDto recordDto) {

        return ResponseEntity.ok(recordService.updateRecord(id, recordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable final Long id) {
        recordService.deleteRecord(id);

        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}/label/{labelId}")
    public ResponseEntity<Void> removeLabelToRecord(@PathVariable final Long id, @PathVariable final Long labelId) {
        recordService.removeLabelFromRecord(id, labelId);

        return ResponseEntity.noContent().build();
    }

}
