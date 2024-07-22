package com.example.address_book.service.contract;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.models.User;

public interface UserService {
    void createUser();
    User getCurrentUser();
}
