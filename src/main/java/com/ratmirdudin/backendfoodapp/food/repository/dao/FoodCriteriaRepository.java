package com.ratmirdudin.backendfoodapp.food.repository.dao;

import com.ratmirdudin.backendfoodapp.food.repository.domain.FoodEntity;
import com.ratmirdudin.backendfoodapp.food.models.PageParams;
import org.springframework.data.domain.Page;

public interface FoodCriteriaRepository {
    Page<FoodEntity> findAllWithFilterTags(PageParams pageParams);
}
