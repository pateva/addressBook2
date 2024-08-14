package com.example.address_book.service.contract;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.UserDto;
import com.example.address_book.model.User;

import java.util.Optional;

public interface UserService {
    void createUser();
    Optional<User> getCurrentUser() throws Exception;
    UserDto addPersonalRecordToUser(Long userId, RecordCreateDto recordCreateDto);
}
