package com.ratmirdudin.backendfoodapp.food.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewFoodDto {
    private String name;
    private String description;
    private Collection<String> tags;
}