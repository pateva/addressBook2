package com.example.address_book.controllers;

import com.example.address_book.auth.CustomUserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class SystemController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getHealth() {

        return new ResponseEntity<>("Health OK", HttpStatus.OK);
    }
}
