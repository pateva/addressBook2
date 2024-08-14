package com.example.address_book.mapper;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.model.Record;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    //TODO fix mapping
    Record mapCreateDtoToEntity(RecordCreateDto recordCreateDto);
    RecordDto mapEntityToDto(Record record);
}
