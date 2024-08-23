package com.example.address_book.service.contract;

import com.example.address_book.dto.NoteCreateDto;

public interface NoteService {
    void createNote(NoteCreateDto noteCreateDto);
}
