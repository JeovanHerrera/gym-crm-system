package com.jeovan.gymcrmsystem.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jeovan.gymcrmsystem.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtToPrincipalConverter {
    public UserDetails convert(DecodedJWT jwt){

        return CustomUserDetail.builder()
                .user(User.builder()
                        .username(jwt.getSubject())
                        .build())
                .build();
    }
}
