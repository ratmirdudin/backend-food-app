package com.ratmirdudin.backendfoodapp.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtSuccessResponse {
    private String accessToken;
    private String refreshToken;
}
