package com.example.address_book.service.impl;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.dto.NoteDto;
import com.example.address_book.dto.NoteUpdateDto;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.NoteMapper;
import com.example.address_book.repository.NoteRepository;
import com.example.address_book.service.contract.NoteService;
import com.example.address_book.service.contract.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.example.address_book.util.Constants.NOTE_DOES_NOT_EXIST_EXCEPTION_MSG;
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

    @Override
    public NoteDto getNoteById(Long id) {

        return noteRepository.findById(id)
                .map(noteMapper::mapEntityToDto)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_DOES_NOT_EXIST_EXCEPTION_MSG, id)));
    }

    @Override
    public NoteDto updateNote(Long id, NoteUpdateDto noteUpdateDto) {
        return noteRepository.findById(id)
                .map(note -> {
                    var noteNew = noteMapper.mapUpdateDtoToEntity(noteUpdateDto);
                    BeanUtils.copyProperties(noteNew, note, "id", "createdAt", "updatedAt", "recordId");

                    return noteMapper.mapEntityToDto(noteRepository.save(note));
                })
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOTE_DOES_NOT_EXIST_EXCEPTION_MSG, noteUpdateDto.getId())));
    }

    @Override
    public void deleteNote(Long id) {
        if(!noteRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(NOTE_DOES_NOT_EXIST_EXCEPTION_MSG, id));
        }

        noteRepository.deleteById(id);
    }
}
