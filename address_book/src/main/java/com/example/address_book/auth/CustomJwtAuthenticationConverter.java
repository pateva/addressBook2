package com.example.address_book.auth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        final String rolesPath = "roles";
        final String emailPath = "email";
        final String nicknamePath = "nickname";
        Collection<SimpleGrantedAuthority> authorities = extractAuthorities(jwt);
        CustomUserPrincipal userPrincipal = new CustomUserPrincipal(
                jwt.getClaimAsString(nicknamePath),
                jwt.getClaimAsString(emailPath),
                jwt.getClaimAsStringList(rolesPath)
        );

        return new CustomAuthenticationToken(userPrincipal, userPrincipal.getRoles(), authorities);
    }

    private Collection<SimpleGrantedAuthority> extractAuthorities(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        return (roles != null ? roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()) : Collections.emptyList());
    }
}

