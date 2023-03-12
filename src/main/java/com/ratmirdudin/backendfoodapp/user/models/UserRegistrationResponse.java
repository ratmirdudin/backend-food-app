package com.ratmirdudin.backendfoodapp.user.models;

import com.ratmirdudin.backendfoodapp.user.enums.RoleEnum;
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
public class UserRegistrationResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private Collection<RoleEnum> roles = new ArrayList<>();
}
