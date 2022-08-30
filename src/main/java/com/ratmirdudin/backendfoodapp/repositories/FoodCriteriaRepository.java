package com.ratmirdudin.backendfoodapp.repositories;

import com.ratmirdudin.backendfoodapp.models.Food;
import org.springframework.data.domain.Page;

public interface FoodCriteriaRepository {
    Page<Food> findAllWithFilterTags(PageParams pageParams);
}
