package com.example.address_book.security;

import com.example.address_book.auth.CustomUserPrincipal;
import com.example.address_book.exception.EntityNotFoundException;
import com.example.address_book.model.User;
import com.example.address_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.example.address_book.util.Constants.USER_DOES_NOT_EXIST_EXCEPTION_MSG;

@Aspect
@Component
@RequiredArgsConstructor
public class UserVerificationAspect {
    private final UserRepository userRepository;

    @Before("@annotation(com.example.address_book.security.VerifyUser) && args(userId,..)")
    public void verifyEmailMatch(JoinPoint joinPoint, Long userId) throws Throwable {
        // Get the logged-in user's email from the security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = null;

        if (principal instanceof CustomUserPrincipal) {
            email = ((CustomUserPrincipal) principal).getEmail();
        }

        if (email == null) {
            throw new SecurityException("User is not authenticated");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(USER_DOES_NOT_EXIST_EXCEPTION_MSG, userId)));

        if (!user.getEmail().equals(email)) {
            throw new SecurityException("Tz tz! This is not yours to see!");
        }
    }
}
