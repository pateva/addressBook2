package com.example.address_book.service;

import com.example.address_book.auth.CustomUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public CustomUserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal) {
            return (CustomUserPrincipal) authentication.getPrincipal();
        }
        throw new IllegalStateException("No authenticated user found");
    }
}
