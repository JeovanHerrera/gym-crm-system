package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.actuator.metrics.TraineeCreationMetric;
import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.security.CustomUserDetail;
import com.jeovan.gymcrmsystem.services.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.jeovan.gymcrmsystem.helpers.ResponseBuilder.buildCredentialResponse;

@RestController
@RequestMapping(EndPoint.TRAINEE)
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeService traineeService;
    private final TraineeCreationMetric traineeCreationMetric;

    @GetMapping(EndPoint.TRAINEE_GET_ALL)
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_ALL_TRAINEES)
    public ResponseEntity<List<Trainee>> getAllUsers() {
        return ResponseEntity.ok(traineeService.getAll());
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_CREATE_TRAINEE)
    public ResponseEntity<CredentialsDTO> createTrainee(@Valid @RequestBody Trainee trainee){
        traineeCreationMetric.increment();
        return ResponseEntity.ok(buildCredentialResponse(traineeService.create(trainee)));
    }

    @PutMapping(EndPoint.TRAINEE_RESET_PASSWORD)
    @Operation(summary = SwaggerConstants.API_OPERATION_UPDATE_TRAINEE_PASSWORD)
    public HttpStatusCode changePassword(@RequestBody CredentialsDTO credentialsDTO){
        traineeService.updatePassword(credentialsDTO);
        return HttpStatus.OK;
    }

    @GetMapping(EndPoint.TRAINEE_USERNAME)
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_TRAINEE)
    public ResponseEntity<Trainee> getTraineeByUsername(@PathVariable String username, @AuthenticationPrincipal(errorOnInvalidType=true) CustomUserDetail userDetail){
        return ResponseEntity.of(traineeService.selectByUsername(username));
    }

    @PutMapping(EndPoint.TRAINEE_USERNAME)
    @Operation(summary = SwaggerConstants.API_OPERATION_UPDATE_TRAINEE_LIST_OF_TRAINERS)
    public ResponseEntity<List<Trainer>> updateTraineeListOfTrainers(@PathVariable String username, @RequestBody List<Trainer> trainers){
        return ResponseEntity.of(Optional.of(traineeService.updateTrainersList(username, trainers)));
    }

    @PatchMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_UPDATE_TRAINEE_ACTIVE_STATUS)
    public HttpStatusCode toggleActiveStatus(@RequestBody User user){
        traineeService.toggleActiveStatus(user);
        return HttpStatus.OK;
    }

    @PutMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_UPDATE_TRAINEE)
    public ResponseEntity<Trainee> updateTraineeProfile(@RequestBody Trainee trainee){
        return ResponseEntity.of(Optional.of(traineeService.update(trainee)));
    }

    @DeleteMapping(EndPoint.TRAINEE_USERNAME)
    @Operation(summary = SwaggerConstants.API_OPERATION_DELETE_TRAINEE)
    public HttpStatusCode deleteTraineeProfile(@PathVariable String username){
        traineeService.deleteByUsername(username);
        return HttpStatus.OK;
    }

}
