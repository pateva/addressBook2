package com.example.address_book.interceptor;

import com.example.address_book.service.contract.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            var user = userService.getCurrentUser();

            if (user.isEmpty()) {
                userService.createUser();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
