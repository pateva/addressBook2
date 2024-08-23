package com.example.address_book.controller;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.dto.NoteDto;
import com.example.address_book.service.contract.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable final Long noteId) {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody final NoteCreateDto noteDto) {
        noteService.createNote(noteDto);

        return ResponseEntity.accepted().build();
    }
}
