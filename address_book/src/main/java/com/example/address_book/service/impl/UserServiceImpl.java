package com.example.address_book.service.impl;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.UserDto;
import com.example.address_book.exception.AuthenticationException;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.mapper.UserMapper;
import com.example.address_book.model.User;
import com.example.address_book.repository.UserRepository;
import com.example.address_book.service.contract.RecordService;
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
    private final RecordService recordService;
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
    public UserDto addPersonalRecordToUser(Long userId, RecordCreateDto recordCreateDto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d does not exist!", userId)));

        var recordDto = recordService.createRecord(recordCreateDto);
        user.setRecordId(recordDto.getId());

        return userMapper.mapEntityToDto(userRepository.save(user));
    }


}

