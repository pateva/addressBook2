package com.example.address_book.service.impl;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.models.User;
import com.example.address_book.repository.UserRepository;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void createUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                //should never happen
                throw new RuntimeException("some exception");
                //todo add custom exception
            }

            if (userRepository.existsByEmail(customUserPrincipal.getEmail())) {
                return;
            }

            userRepository.saveAndFlush(User.builder().email(customUserPrincipal.getEmail()).build());
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                //should never happen
                throw new RuntimeException("some exception");
                //todo add custom exception
            }

            Optional<User> user = userRepository.findByEmail(customUserPrincipal.getEmail());
            if (user.isEmpty()) {
                //todo add custom exception
            }

            return user.get();
        }

        throw new RuntimeException("test");
    }
}

