package com.ratmirdudin.backendfoodapp.mappers;

import com.ratmirdudin.backendfoodapp.dtos.UserDto;
import com.ratmirdudin.backendfoodapp.dtos.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public User toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public User toEntity(UserRegistrationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
