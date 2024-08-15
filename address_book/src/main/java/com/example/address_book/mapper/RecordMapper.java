package com.example.address_book.mapper;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.model.Record;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ContactDetailMapper.class, NoteMapper.class})
public interface RecordMapper {
    Record mapCreateDtoToEntity(RecordCreateDto createDto);
    RecordDto mapEntityToDto(Record entity);
}
