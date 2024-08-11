package com.example.address_book.service.contract;

import com.example.address_book.models.User;

import java.util.Optional;

public interface UserService {
    void createUser();
    Optional<User> getCurrentUser();
}
