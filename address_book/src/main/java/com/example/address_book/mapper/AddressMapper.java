package com.example.address_book.mapper;

import com.example.address_book.dto.AddressCreateDto;
import com.example.address_book.dto.AddressDto;
import com.example.address_book.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address mapDtoToEntity(AddressDto dto);
    AddressDto mapEntityToDto(Address entity);
    Address mapCreateDtoToEntity(AddressCreateDto dto);
}
