package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.TraineeService;
import com.jeovan.gymcrmsystem.services.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jeovan.gymcrmsystem.helpers.ResponseBuilder.buildCredentialResponse;

@RestController
@RequestMapping(EndPoint.TRAINER)
@AllArgsConstructor
public class TrainerController {
    @Autowired
    private final TrainerService trainerService;

    @GetMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_ALL_USERS)
    public ResponseEntity<List<Trainer>> getAllUsers() {
        return ResponseEntity.ok(trainerService.getAll());
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_CREATE_TRAINER)
    public ResponseEntity<Credentials> createTrainer(@RequestBody Trainer trainer){
        return ResponseEntity.ok(buildCredentialResponse(trainerService.create(trainer)));
    }

    @PutMapping(EndPoint.TRAINER_RESET_PASSWORD)
    public ResponseEntity<String> changePassword(@RequestBody Credentials credentials){
        trainerService.updatePassword(credentials);
        return ResponseEntity.ok("OK");
    }

    @GetMapping(EndPoint.TRAINER_USERNAME)
    public ResponseEntity<Trainer> getTraineeByUsername(@PathVariable String username){
        return ResponseEntity.of(trainerService.selectByUsername(username));
    }

    @PutMapping
    public ResponseEntity<Trainer> updateTraineeProfile(@RequestBody Trainer trainer){
        return ResponseEntity.ok(trainerService.update(trainer));
    }

    @PatchMapping
    public HttpStatusCode toggleActiveStatus(@RequestBody User user){
        trainerService.toggleActiveStatus(user);
        return HttpStatus.OK;
    }


}
