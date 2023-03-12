package com.ratmirdudin.backendfoodapp.food.repository.domain;

import com.ratmirdudin.backendfoodapp.common.audit.DateAudit;
import com.ratmirdudin.backendfoodapp.user.repository.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "t_weekly")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyEntity extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "food_id")
    private FoodEntity food;
}