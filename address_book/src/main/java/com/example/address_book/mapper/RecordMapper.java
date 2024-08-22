package com.example.address_book.mapper;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.model.Record;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ContactDetailMapper.class, NoteMapper.class})
public interface RecordMapper {
    Record mapCreateDtoToEntity(RecordCreateDto createDto);
    RecordDto mapEntityToDto(Record entity);
    RecordPartialDto mapEntityToPartialDto(Record entity);
    List<RecordPartialDto> mapEntityListToPartialDtoList(List<Record> entityList);
    Record mapRecordUpdateDtoToEntity(RecordUpdateDto recordUpdateDto);
}
