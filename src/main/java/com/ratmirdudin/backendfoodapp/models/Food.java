package com.ratmirdudin.backendfoodapp.models;

import com.ratmirdudin.backendfoodapp.models.audit.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NamedEntityGraph(
        name = "food-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("tags"),
        }
)
@Entity
@Table(name = "t_food")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_food_tag",
            joinColumns = @JoinColumn(name = "food_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags = new ArrayList<>();
}
