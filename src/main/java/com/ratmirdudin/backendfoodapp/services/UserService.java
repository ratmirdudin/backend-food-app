package com.ratmirdudin.backendfoodapp.services;

import com.ratmirdudin.backendfoodapp.dtos.UserDto;
import com.ratmirdudin.backendfoodapp.dtos.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.enums.RoleEnum;
import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.mappers.UserMapper;
import com.ratmirdudin.backendfoodapp.models.Role;
import com.ratmirdudin.backendfoodapp.models.User;
import com.ratmirdudin.backendfoodapp.repositories.RoleRepository;
import com.ratmirdudin.backendfoodapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto saveUser(UserRegistrationDto userRegistrationDto) {
        log.info("Saving new user with username: {}", userRegistrationDto.getUsername());
        User user = userMapper.toEntity(userRegistrationDto);
        Role role = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(
                () -> new ResourceNotFoundException("Role with name: " + RoleEnum.ROLE_USER + " not found"));
        user.setRoles(Set.of(role));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username: " + username + " not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> getRandomUsers(Long limit) {
        return userRepository.findAllOrderByRandom(limit)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
