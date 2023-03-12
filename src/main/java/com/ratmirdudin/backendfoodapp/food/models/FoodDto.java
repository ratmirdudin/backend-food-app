package com.ratmirdudin.backendfoodapp.food.models;

import com.ratmirdudin.backendfoodapp.food.repository.domain.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    private Long id;
    private String name;
    private String description;
    private Collection<TagEntity> tagEntities = new ArrayList<>();
}
