package com.ratmirdudin.backendfoodapp.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
}
