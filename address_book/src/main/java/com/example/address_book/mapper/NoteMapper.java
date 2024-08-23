package com.example.address_book.mapper;

import com.example.address_book.dto.NoteCreateDto;
import com.example.address_book.dto.NoteDto;
import com.example.address_book.model.Note;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    Note mapDtoToEntity(NoteDto dto);
    NoteDto mapEntityToDto(Note entity);
    Set<NoteDto> mapEntitySetToDtoSet(Set<Note> entitySet);
    Set<Note> mapDtoSetToEntitySet(Set<NoteDto> dtoSet);
    Note mapCreateDtoToEntity(NoteCreateDto createDto);
}
