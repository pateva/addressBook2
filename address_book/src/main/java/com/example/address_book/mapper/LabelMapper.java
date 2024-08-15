package com.example.address_book.mapper;

import com.example.address_book.dto.LabelDto;
import com.example.address_book.model.Label;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface LabelMapper {
    Label mapDtoToEntity(LabelDto dto);
    LabelDto mapEntityToDto(Label entity);
    Set<Label> mapDtoSetToEntitySet(Set<LabelDto> dtoSet);
    Set<LabelDto> mapEntitySetToDtoSet(Set<Label> entitySet);
}
