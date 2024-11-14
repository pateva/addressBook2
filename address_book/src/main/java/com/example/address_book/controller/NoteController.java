package com.example.address_book.controller;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.dto.NoteDto;
import com.example.address_book.dto.NoteUpdateDto;
import com.example.address_book.service.contract.NoteService;
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
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable final Long id) {

        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNote(@RequestBody final NoteCreateDto noteDto) {
        noteService.createNote(noteDto);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable final Long id, @RequestBody final NoteUpdateDto noteDto) {

        return ResponseEntity.ok(noteService.updateNote(id, noteDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable final Long id) {
        noteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}
