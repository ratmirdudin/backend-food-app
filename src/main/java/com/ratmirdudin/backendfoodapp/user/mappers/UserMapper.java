package com.ratmirdudin.backendfoodapp.user.mappers;

import com.ratmirdudin.backendfoodapp.user.models.UserRegistrationResponse;
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

    public UserEntity toEntity(UserRegistrationResponse dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserEntity toEntity(UserRegistrationDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, UserEntity.class);
    }

    public UserRegistrationResponse toRegistered(UserEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserRegistrationResponse.class);
    }
}
