package com.ratmirdudin.backendfoodapp.user.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.security.jwt.JwtProvider;
import com.ratmirdudin.backendfoodapp.security.jwt.JwtSuccessResponse;
import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
import com.ratmirdudin.backendfoodapp.user.repository.dao.UserRepository;
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

        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username: " + username + " not found"));

        List<String> authorities = userEntity.getRoles()
                .stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        return jwtProvider.generateTokens(userEntity.getUsername(), authorities);
    }
}
