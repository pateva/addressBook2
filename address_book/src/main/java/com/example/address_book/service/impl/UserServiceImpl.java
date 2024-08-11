package com.example.address_book.service.impl;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.models.User;
import com.example.address_book.repository.UserRepository;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                //TODO add proper exception
                throw new RuntimeException("should also never happen");
            }

            System.err.println(customUserPrincipal.getEmail());
            return userRepository.findByEmail(customUserPrincipal.getEmail());
        }

        throw new RuntimeException("This should never happen");
    }
}

