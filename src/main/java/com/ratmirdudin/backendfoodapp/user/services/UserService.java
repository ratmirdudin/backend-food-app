package com.ratmirdudin.backendfoodapp.user.services;

import com.ratmirdudin.backendfoodapp.user.models.UserDto;
import com.ratmirdudin.backendfoodapp.user.models.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.user.enums.RoleEnum;
import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.user.mappers.UserMapper;
import com.ratmirdudin.backendfoodapp.user.repository.dao.RoleRepository;
import com.ratmirdudin.backendfoodapp.user.repository.dao.UserRepository;
import com.ratmirdudin.backendfoodapp.user.repository.domain.RoleEntity;
import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
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
        UserEntity userEntity = userMapper.toEntity(userRegistrationDto);
        RoleEntity roleEntity = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(
                () -> new ResourceNotFoundException("Role with name: " + RoleEnum.ROLE_USER + " not found"));
        userEntity.setRoleEntities(Set.of(roleEntity));
        userEntity.setEnabled(true);
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        return userMapper.toDto(userRepository.save(userEntity));
    }

    public UserDto getUserByUsername(String username) {
        log.info("Fetching user with username: {}", username);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User with username: " + username + " not found"));
        return userMapper.toDto(userEntity);
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
