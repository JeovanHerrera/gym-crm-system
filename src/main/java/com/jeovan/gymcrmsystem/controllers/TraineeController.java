package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
//import io.swagger.annotations.ApiOperation;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping(EndPoint.TRAINEE_RESET_PASSWORD)
    public HttpStatusCode changePassword(@RequestBody Credentials credentials){
        traineeService.updatePassword(credentials);
        return HttpStatus.OK;
    }

    @GetMapping(EndPoint.TRAINEE_USERNAME)
    public ResponseEntity<Trainee> getTraineeByUsername(@PathVariable String username){
        return ResponseEntity.of(traineeService.selectByUsername(username));
    }

    @PutMapping(EndPoint.TRAINEE_USERNAME)
    public ResponseEntity<List<Trainer>> updateTraineeListOfTrainers(@PathVariable String username, @RequestBody List<Trainer> trainers){
        return ResponseEntity.of(Optional.of(traineeService.updateTrainersList(username, trainers)));
    }

    @PatchMapping
    public HttpStatusCode toggleActiveStatus(@RequestBody User user){
        traineeService.toggleActiveStatus(user);
        return HttpStatus.OK;
    }

    @PutMapping
    public ResponseEntity<Trainee> updateTraineeProfile(@RequestBody Trainee trainee){
        return ResponseEntity.of(Optional.of(traineeService.update(trainee)));
    }

    @DeleteMapping(EndPoint.TRAINEE_USERNAME)
    public HttpStatusCode deleteTraineeProfile(@PathVariable String username){
        traineeService.deleteByUsername(username);
        return HttpStatus.OK;
    }

}
