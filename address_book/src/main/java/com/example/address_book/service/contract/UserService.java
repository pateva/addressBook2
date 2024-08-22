package com.example.address_book.service.contract;

import com.example.address_book.dto.UserDto;
import com.example.address_book.model.User;

import java.util.Optional;

public interface UserService {
    void createUser();
    Optional<User> getCurrentUser() throws Exception;
    void validateUser(Long userId);
    UserDto getUserById(Long id);
}
