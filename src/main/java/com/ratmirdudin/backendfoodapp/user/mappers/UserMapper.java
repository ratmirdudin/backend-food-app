package com.ratmirdudin.backendfoodapp.user.mappers;

import com.ratmirdudin.backendfoodapp.user.models.UserDto;
import com.ratmirdudin.backendfoodapp.user.models.UserRegistrationDto;
import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public UserEntity toEntity(UserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserEntity toEntity(UserRegistrationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserDto toDto(UserEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
