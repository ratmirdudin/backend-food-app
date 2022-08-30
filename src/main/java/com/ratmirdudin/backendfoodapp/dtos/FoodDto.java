package com.ratmirdudin.backendfoodapp.dtos;

import com.ratmirdudin.backendfoodapp.models.Tag;
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
    private Collection<Tag> tags = new ArrayList<>();
}
