package com.ratmirdudin.backendfoodapp.security;

import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.models.User;
import com.ratmirdudin.backendfoodapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Fetching user with username: {}", username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username: " + username + " not found"));
        return UserPrincipal.create(user);
    }
}
