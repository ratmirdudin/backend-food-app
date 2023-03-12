package com.ratmirdudin.backendfoodapp.food.repository.dao;

import com.ratmirdudin.backendfoodapp.food.repository.domain.FoodEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long>, FoodCriteriaRepository {

    @EntityGraph(value = "food-entity-graph")
    List<FoodEntity> findAll();

    @EntityGraph(value = "food-entity-graph")
    Optional<FoodEntity> findById(Long id);

//    @EntityGraph(value = "user-entity-graph")
//    @Query(value = "select * from t_users order by random() limit :limit", nativeQuery = true)
//    List<User> findAllOrderByRandom(@Param("limit") Long limit);
}
