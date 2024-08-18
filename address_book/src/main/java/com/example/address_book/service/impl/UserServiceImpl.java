package com.example.address_book.service.impl;

import com.example.address_book.auth.CustomUserPrincipal;;
import com.example.address_book.dto.UserDto;
import com.example.address_book.exception.AuthenticationException;
import com.example.address_book.mapper.UserMapper;
import com.example.address_book.model.User;
import com.example.address_book.repository.UserRepository;
import com.example.address_book.service.contract.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.address_book.util.Constants.USER_DOES_NOT_EXIST_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                throw new AuthenticationException("Email address is missing or principal is incorrect");
            }

            if (userRepository.existsByEmail(customUserPrincipal.getEmail())) {
                return;
            }

            var user = User.builder().email(customUserPrincipal.getEmail()).build();
            userRepository.save(user);
        }
    }

    @Override
    public Optional<User> getCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                throw new AuthenticationException("Email address is missing or principal is incorrect");
            }

            System.err.println(customUserPrincipal.getEmail());
            return userRepository.findByEmail(customUserPrincipal.getEmail());
        }

        throw new AuthenticationException("Authentication issue!");
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format(USER_DOES_NOT_EXIST_EXCEPTION_MSG, id)));

        return userMapper.mapEntityToDto(user);
    }

}

