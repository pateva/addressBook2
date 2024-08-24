package com.example.address_book.mapper;

import com.example.address_book.dto.ContactDetailCreateDto;
import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.model.ContactDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ContactDetailMapper {
    @Mapping(target = "record.id", source = "recordId")
    ContactDetail mapDtoToEntity(ContactDetailDto dto);
    @Mapping(target = "recordId", source = "record.id")
    ContactDetailDto mapEntityToDto(ContactDetail entity);
    Set<ContactDetail> mapDtoSetToEntitySet(Set<ContactDetailDto> dtoSet);
    Set<ContactDetailDto> mapEntitySetToDtoSet(Set<ContactDetail> entitySet);
    @Mapping(target = "record.id", source = "recordId")
    ContactDetail mapCreateDtoToEntity(ContactDetailCreateDto dto);
}
