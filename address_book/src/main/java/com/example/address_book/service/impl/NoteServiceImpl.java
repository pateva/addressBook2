package com.example.address_book.service.impl;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.NoteMapper;
import com.example.address_book.repository.NoteRepository;
import com.example.address_book.service.contract.NoteService;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.address_book.util.Constants.RECORD_DOES_NOT_EXIST_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final RecordService recordService;
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public void createNote(NoteCreateDto noteCreateDto) {
        if (noteCreateDto.getRecordId() != null && !recordService.existsById(noteCreateDto.getRecordId())) {
            throw new EntityNotFoundException(String.format(RECORD_DOES_NOT_EXIST_EXCEPTION_MSG, noteCreateDto.getRecordId()));
        }

        var entity = noteMapper.mapCreateDtoToEntity(noteCreateDto);
        noteRepository.save(entity);
    }
}
