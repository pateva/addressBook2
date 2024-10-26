package com.example.address_book.mapper;

import com.example.address_book.dto.UserDto;
import com.example.address_book.dto.UserPartialDto;
import com.example.address_book.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RecordMapper.class, LabelMapper.class})
public interface UserMapper {
    UserDto mapEntityToDto(User user);
    @Mapping(source = "records", target = "personalRecords")
    UserPartialDto mapEntityToPartialDto(User user);
}
