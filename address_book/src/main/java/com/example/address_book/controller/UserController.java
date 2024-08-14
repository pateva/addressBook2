package com.example.address_book.controller;

import com.example.address_book.dto.RecordCreateDto;
import com.example.address_book.dto.UserDto;
import com.example.address_book.service.contract.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/record/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> createPersonalRecord(@PathVariable @NonNull final Long userId,
                                                        @RequestBody @NonNull final RecordCreateDto recordDto) {

        return new ResponseEntity<>(userService.addPersonalRecordToUser(userId, recordDto), HttpStatus.OK);
    }
}
