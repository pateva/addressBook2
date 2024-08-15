package com.example.address_book.mapper;

import com.example.address_book.dto.ContactDetailDto;
import com.example.address_book.model.ContactDetail;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface ContactDetailMapper {
    ContactDetail mapDtoToEntity(ContactDetailDto dto);
    ContactDetailDto mapEntityToDto(ContactDetail entity);
    Set<ContactDetail> mapDtoSetToEntitySet(Set<ContactDetailDto> dtoSet);
    Set<ContactDetailDto> mapEntitySetToDtoSet(Set<ContactDetail> entitySet);
}
