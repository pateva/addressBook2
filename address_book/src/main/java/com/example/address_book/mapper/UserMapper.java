package com.example.address_book.mapper;

import com.example.address_book.dto.UserDto;
import com.example.address_book.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //TODO probably also fix mapping
    UserDto mapEntityToDto(User user);
}
