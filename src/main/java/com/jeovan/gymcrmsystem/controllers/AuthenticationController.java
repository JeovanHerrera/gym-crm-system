package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoint.LOGIN)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_LOG_IN)
    public ResponseEntity<String> login(@RequestBody CredentialsDTO credentialsDTO){
        authenticationService.authenticateUser(credentialsDTO.getUsername(), credentialsDTO.getPassword());
        return ResponseEntity.ok("OK");
    }


}
