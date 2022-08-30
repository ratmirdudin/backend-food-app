package com.ratmirdudin.backendfoodapp.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ratmirdudin.backendfoodapp.security.SecurityConstants;
import com.ratmirdudin.backendfoodapp.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private final long hour = 60 * 60 * 1000L;

    public String generateAccessToken(String username, List<String> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        long currentTimeMillis = System.currentTimeMillis();
        Date expiresAt = new Date(currentTimeMillis + hour);
//        Date expiresAt = new Date(currentTimeMillis + 60 * 1000L); // 1 minute
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiresAt)
                .withClaim("roles", authorities)
                .sign(algorithm);
    }

    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        long currentTimeMillis = System.currentTimeMillis();
        Date expiresAt = new Date(currentTimeMillis + 30L * 24 * hour);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public JwtSuccessResponse generateTokens(String username, List<String> authorities) {

        String accessToken = generateAccessToken(username, authorities);
        String refreshToken = generateRefreshToken(username);
        return new JwtSuccessResponse(accessToken, refreshToken);
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public boolean isIncorrectAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.TOKEN_PREFIX);
    }

    public String extractTokenFromAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader.substring(SecurityConstants.TOKEN_PREFIX.length());
    }
}
