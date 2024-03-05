package com.jeovan.gymcrmsystem.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtIssuer {

    @Value("${gym-crm-system.jwt.secret}")
    private String secretKey;

    @Value("${gym-crm-system.jwt.expiration-in-milliseconds}")
    private int jwtExpirationMs;

    @Value("${gym-crm-system.jwt.issuer}")
    private String jwtIssuer;

    public String issue(CustomUserDetail user){
        return JWT
                .create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusMillis(jwtExpirationMs))
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withIssuer(jwtIssuer)
                .sign(Algorithm.HMAC256(secretKey));
    }
}
