package com.ratmirdudin.backendfoodapp.api;

import com.ratmirdudin.backendfoodapp.dtos.FoodDto;
import com.ratmirdudin.backendfoodapp.dtos.CustomPageDto;
import com.ratmirdudin.backendfoodapp.repositories.PageParams;
import com.ratmirdudin.backendfoodapp.services.FoodService;
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
public class FoodApi {

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
