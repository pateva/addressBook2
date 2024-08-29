package com.example.address_book.service.contract;

import com.example.address_book.dto.UserDto;
import com.example.address_book.model.User;

import java.util.Optional;

public interface UserService {
    void createUser();

    UserDto getCurrentUser();

    void validateUser(Long userId);

    UserDto getUserById(Long id);
}
