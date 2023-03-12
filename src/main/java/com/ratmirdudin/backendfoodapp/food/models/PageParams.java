package com.ratmirdudin.backendfoodapp.food.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {
    private int page;
    private int limit;
    private Sort.Direction sortDir;
    private String sortBy;
}

