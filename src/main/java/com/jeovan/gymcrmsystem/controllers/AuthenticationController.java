package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.dtos.responses.AuthTokenDTO;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.security.CustomUserDetail;
import com.jeovan.gymcrmsystem.security.JwtIssuer;
import com.jeovan.gymcrmsystem.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(EndPoint.LOGIN)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtIssuer jwtIssuer;

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_LOG_IN)
    public ResponseEntity<AuthTokenDTO> login(@RequestBody CredentialsDTO credentialsDTO){
        String token = jwtIssuer.issue((CustomUserDetail) authenticationService.authenticateUser(credentialsDTO.getUsername(), credentialsDTO.getPassword()).getPrincipal());
        return ResponseEntity.ok(
                AuthTokenDTO
                        .builder().message("OK")
                        .data(new ArrayList<>(List.of(token)))
                        .success(true)
                        .build());
    }
}
