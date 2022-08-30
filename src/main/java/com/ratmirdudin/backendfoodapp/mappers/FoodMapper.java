package com.ratmirdudin.backendfoodapp.mappers;

import com.ratmirdudin.backendfoodapp.dtos.FoodDto;
import com.ratmirdudin.backendfoodapp.models.Food;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FoodMapper {
    private final ModelMapper mapper;

    public Food toEntity(FoodDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Food.class);
    }

    public FoodDto toDto(Food entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, FoodDto.class);
    }
}
