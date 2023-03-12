package com.ratmirdudin.backendfoodapp.food.services;

import com.ratmirdudin.backendfoodapp.food.models.CustomPageDto;
import com.ratmirdudin.backendfoodapp.food.models.FoodDto;
import com.ratmirdudin.backendfoodapp.food.models.PageMetaDto;
import com.ratmirdudin.backendfoodapp.exceptions.ResourceNotFoundException;
import com.ratmirdudin.backendfoodapp.food.mappers.FoodMapper;
import com.ratmirdudin.backendfoodapp.food.repository.domain.FoodEntity;
import com.ratmirdudin.backendfoodapp.food.models.PageParams;
import com.ratmirdudin.backendfoodapp.food.repository.dao.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FoodService {

    private final FoodRepository foodRepository;

    private final FoodMapper foodMapper;

    public CustomPageDto getAllFoods(PageParams pageParams) {
        if (pageParams.getPage() < 1) {
            pageParams.setPage(1);
        }
        log.info("Fetching all foods for page: {} and limit: {}", pageParams.getPage(), pageParams.getLimit());
        Page<FoodEntity> foodPage = foodRepository.findAllWithFilterTags(pageParams);
        int totalPages = foodPage.getTotalPages();
        long totalElements = foodPage.getTotalElements();
        int size = foodPage.getSize();
        int pageNumber = foodPage.getNumber() + 1;
        PageMetaDto pageMetaDto = new PageMetaDto(pageNumber, size, totalElements, totalPages);

        List<FoodDto> foodDtoList = foodPage.getContent().stream()
                .map(foodMapper::toDto)
                .collect(Collectors.toList());
        return new CustomPageDto(pageMetaDto, foodDtoList);
    }

    public FoodDto getFoodById(Long id) {
        log.info("Fetching food with id: {}", id);
        FoodEntity entity = foodRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Food with id " + id + " not found"));
        return foodMapper.toDto(entity);
    }

    public FoodDto saveFood(FoodDto foodDto) {
        log.info("Saving new food with name: {}", foodDto.getName());
        FoodEntity save = foodRepository.save(foodMapper.toEntity(foodDto));
        return foodMapper.toDto(save);
    }
}
