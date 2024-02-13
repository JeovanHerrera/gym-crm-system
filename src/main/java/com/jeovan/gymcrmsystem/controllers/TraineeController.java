package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
//import io.swagger.annotations.ApiOperation;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jeovan.gymcrmsystem.helpers.ResponseBuilder.buildCredentialResponse;

@RestController
@RequestMapping(EndPoint.TRAINEE)
@AllArgsConstructor
public class TraineeController {

    @Autowired
    private final TraineeService traineeService;

    @GetMapping("/all")
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_ALL_USERS)
    public ResponseEntity<List<Trainee>> getAllUsers() {
        return ResponseEntity.ok(traineeService.getAll());
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_CREATE_TRAINEE)
    public ResponseEntity<Credentials> createTrainee(@RequestBody Trainee trainee){
        return ResponseEntity.ok(buildCredentialResponse(traineeService.create(trainee)));
    }

    @PutMapping
    public ResponseEntity<String> changePassword(@RequestBody Credentials credentials){
        traineeService.updatePassword(credentials);
        return ResponseEntity.ok("OK");
    }

    @GetMapping(EndPoint.TRAINEE_USERNAME)
    public ResponseEntity<Trainee> getTraineeByUsername(@PathVariable @NotNull String username){
        return ResponseEntity.ok(traineeService.selectByUsername(username));
    }


}
