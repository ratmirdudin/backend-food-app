package com.ratmirdudin.backendfoodapp.food.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageMetaDto {
    private int pageNumber;
    private int size;
    private long totalElements;
    private int totalPages;
}
