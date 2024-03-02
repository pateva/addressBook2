package com.example.address_book.controllers;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.service.TestService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class RecordsController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getRecords(@AuthenticationPrincipal CustomUserPrincipal customUserPrincipal) {
//        System.err.println(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println(customUserPrincipal);

        return new ResponseEntity<>("Returned records", HttpStatus.OK);
    }
}
