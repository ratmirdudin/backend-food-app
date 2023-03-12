package com.ratmirdudin.backendfoodapp.food.mappers;

import com.ratmirdudin.backendfoodapp.food.models.FoodDto;
import com.ratmirdudin.backendfoodapp.food.repository.domain.FoodEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FoodMapper {
    private final ModelMapper mapper;

    public FoodEntity toEntity(FoodDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, FoodEntity.class);
    }

    public FoodDto toDto(FoodEntity entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, FoodDto.class);
    }
}
