package com.example.address_book.service.contract;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.dto.NoteDto;
import com.example.address_book.dto.NoteUpdateDto;

public interface NoteService {
    void createNote(NoteCreateDto noteCreateDto);
    NoteDto getNoteById(Long id);
    NoteDto updateNote(NoteUpdateDto noteUpdateDto);

}
