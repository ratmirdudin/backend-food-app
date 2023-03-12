package com.ratmirdudin.backendfoodapp.food.repository.domain;

import com.ratmirdudin.backendfoodapp.common.audit.DateAudit;
import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "t_favourite_food")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteFoodEntity extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id")
    private FoodEntity foodEntity;
}