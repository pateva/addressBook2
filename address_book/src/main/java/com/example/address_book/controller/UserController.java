package com.example.address_book.controller;

import com.example.address_book.dto.UserDto;
import com.example.address_book.dto.UserPartialDto;
import com.example.address_book.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserPartialDto> createUser() {

        return ResponseEntity.ok(userService.createUser());
    }

    @GetMapping
    public ResponseEntity<UserPartialDto> getUser() {

        return ResponseEntity.ok(userService.getCurrentUser());
    }

}
