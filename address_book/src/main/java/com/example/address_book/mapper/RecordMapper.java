package com.example.address_book.mapper;

import com.example.address_book.dto.PagedRecordDto;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.RecordDto;
import com.example.address_book.dto.RecordPartialDto;
import com.example.address_book.dto.RecordUpdateDto;
import com.example.address_book.model.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ContactDetailMapper.class, NoteMapper.class})
public interface RecordMapper {
    Record mapCreateDtoToEntity(RecordCreateDto createDto);

    RecordDto mapEntityToDto(Record entity);

    RecordPartialDto mapEntityToPartialDto(Record entity);

    List<RecordPartialDto> mapEntityListToPartialDtoList(List<Record> entityList);

    Record mapRecordUpdateDtoToEntity(RecordUpdateDto recordUpdateDto);

    @Mapping(target = "totalPages", expression = "java(recordPage.getTotalPages())")
    @Mapping(target = "totalElements", expression = "java(recordPage.getTotalElements())")
    @Mapping(target = "recordDtoList", source = "content") // Automatically maps the list using the toRecordDtoList method
    PagedRecordDto toPagedRecordDto(Page<Record> recordPage);
}
