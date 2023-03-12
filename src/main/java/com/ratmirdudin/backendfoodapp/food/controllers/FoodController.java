package com.ratmirdudin.backendfoodapp.food.controllers;

import com.ratmirdudin.backendfoodapp.food.models.FoodDto;
import com.ratmirdudin.backendfoodapp.food.models.CustomPageDto;
import com.ratmirdudin.backendfoodapp.food.models.PageParams;
import com.ratmirdudin.backendfoodapp.food.services.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<CustomPageDto> getAllFoods(@RequestParam(defaultValue = "") List<String> tags,
                                                     @RequestParam(defaultValue = "1") int page,
                                                     @RequestParam(defaultValue = "10") int limit,
                                                     @RequestParam(defaultValue = "asc") String sortDir,
                                                     @RequestParam(defaultValue = "name") String sortBy) {
        Sort.Direction dir = sortDir.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return ResponseEntity.ok(foodService.getAllFoods(new PageParams(page, limit, dir, sortBy)));
    }

    @PostMapping
    public ResponseEntity<FoodDto> saveFood(@RequestBody FoodDto foodDto) {
        return ResponseEntity.ok(foodService.saveFood(foodDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDto> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

}
