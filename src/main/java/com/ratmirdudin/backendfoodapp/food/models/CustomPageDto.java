package com.ratmirdudin.backendfoodapp.food.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPageDto {
    private PageMetaDto pageMeta;
    private Object data;
}
