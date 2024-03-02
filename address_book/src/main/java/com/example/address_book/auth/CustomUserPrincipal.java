package com.example.address_book.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomUserPrincipal {
    private String username;
    private String email;
    private List<String> roles;

    @Override
    public String toString() {
        return "CustomUserPrincipal{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}
