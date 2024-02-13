package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.services.AuthenticationService;
import com.jeovan.gymcrmsystem.services.TraineeService;
import com.jeovan.gymcrmsystem.services.UserAuxService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jeovan.gymcrmsystem.helpers.ResponseBuilder.buildCredentialResponse;

@RestController
@RequestMapping(EndPoint.LOGIN)
@AllArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @GetMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_LOG_IN)
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
        authenticationService.authenticateUser(username, password);
        return ResponseEntity.ok("OK");
    }


}
