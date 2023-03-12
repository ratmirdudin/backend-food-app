package com.ratmirdudin.backendfoodapp.user.controllers;

import com.ratmirdudin.backendfoodapp.user.models.UserRegistrationResponse;
import com.ratmirdudin.backendfoodapp.user.models.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.user.services.AuthService;
import com.ratmirdudin.backendfoodapp.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthService authService;


    @GetMapping
    public ResponseEntity<List<UserRegistrationResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserRegistrationResponse> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/me")
    public ResponseEntity<UserRegistrationResponse> getCurrentUser() {
        String currentUsername = authService.getCurrentUsername();
        return ResponseEntity.ok(userService.getUserByUsername(currentUsername));
    }

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> saveUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        UserRegistrationResponse saveUser = userService.saveUser(userRegistrationDto);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/" + saveUser.getUsername()).toUriString());
        return ResponseEntity.created(uri).body(saveUser);
    }

    @GetMapping("/getRandom")
    public ResponseEntity<List<UserRegistrationResponse>> getRandomUsers(@RequestParam(defaultValue = "1") Long limit) {
        return ResponseEntity.ok(userService.getRandomUsers(limit));
    }
}
