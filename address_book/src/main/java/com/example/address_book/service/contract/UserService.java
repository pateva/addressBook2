package com.example.address_book.service.contract;

import com.example.address_book.dto.UserDto;
import com.example.address_book.dto.UserPartialDto;

public interface UserService {
    UserPartialDto createUser();

    UserPartialDto getCurrentUser();

    void validateUser(Long userId);

    UserDto getUserById(Long id);
}
