package com.ratmirdudin.backendfoodapp.api;

import com.ratmirdudin.backendfoodapp.dtos.UserDto;
import com.ratmirdudin.backendfoodapp.dtos.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.services.AuthService;
import com.ratmirdudin.backendfoodapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final AuthService authService;
    private final UserService userService;

    // @PostMapping(/login) realized via class JwtUsernamePasswordAuthenticationFilter

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserDto saveUser = userService.saveUser(userRegistrationDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/" + saveUser.getUsername()).toUriString());
        return ResponseEntity.created(uri).body(saveUser);
    }

    @GetMapping("/refreshToken/{token}")
    public ResponseEntity<?> refreshToken(@PathVariable String token) {
        return ResponseEntity.ok(authService.refreshToken(token));
    }

}
