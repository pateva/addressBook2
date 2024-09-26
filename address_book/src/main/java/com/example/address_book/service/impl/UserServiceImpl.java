package com.example.address_book.service.impl;

import com.example.address_book.auth.CustomUserPrincipal;;
import com.example.address_book.dto.UserDto;
import com.example.address_book.exception.AuthenticationException;
import com.example.address_book.exception.EntityAlreadyExistsException;
import com.example.address_book.mapper.UserMapper;
import com.example.address_book.model.User;
import com.example.address_book.repository.UserRepository;
import com.example.address_book.service.contract.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import static com.example.address_book.util.Constants.USER_DOES_NOT_EXIST_EXCEPTION_MSG;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                throw new AuthenticationException("Email address is missing or principal is incorrect");
            }

            if (userRepository.existsByEmail(customUserPrincipal.getEmail())) {
                throw new EntityAlreadyExistsException("User already exists! Something is not ok");
            }

            var user = User.builder().email(customUserPrincipal.getEmail()).build();

            return userMapper.mapEntityToDto(userRepository.save(user));
        }

        throw new RuntimeException("Something is not ok");
    }

    @Override
    public UserDto getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserPrincipal customUserPrincipal) {
            if (customUserPrincipal.getEmail() == null || customUserPrincipal.getEmail().isEmpty()) {
                throw new AuthenticationException("Email address is missing or principal is incorrect");
            }

            return userMapper.mapEntityToDto(userRepository.findByEmail(customUserPrincipal.getEmail()).orElse(null));
        }

        return null;
    }

    @Override
    public void validateUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new EntityNotFoundException(String.format("User with id %d does not exist!", userId));
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format(USER_DOES_NOT_EXIST_EXCEPTION_MSG, id)));

        return userMapper.mapEntityToDto(user);
    }

}

