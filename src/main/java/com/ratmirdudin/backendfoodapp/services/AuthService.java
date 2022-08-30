package com.ratmirdudin.backendfoodapp.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.jwt.JwtProvider;
import com.ratmirdudin.backendfoodapp.jwt.JwtSuccessResponse;
import com.ratmirdudin.backendfoodapp.models.User;
import com.ratmirdudin.backendfoodapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public JwtSuccessResponse refreshToken(String token) {
        log.info("Refreshing token by refresh token: {}", token);
        DecodedJWT decodedJWT = jwtProvider.verifyToken(token);
        String username = decodedJWT.getSubject();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username: " + username + " not found"));

        List<String> authorities = user.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        return jwtProvider.generateTokens(user.getUsername(), authorities);
    }
}
