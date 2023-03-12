package com.ratmirdudin.backendfoodapp.user.models;

import com.ratmirdudin.backendfoodapp.user.repository.domain.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private Collection<RoleEntity> roleEntities = new ArrayList<>();
}
