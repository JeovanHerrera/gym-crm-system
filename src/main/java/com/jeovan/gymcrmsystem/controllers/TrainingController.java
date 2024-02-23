package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.services.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoint.TRAINING)
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

    @GetMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_ALL_TRAININGS)
    private ResponseEntity<List<Training>> getAllTrainings(){
        return ResponseEntity.ok(trainingService.getAll());
    }

    @PostMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_CREATE_TRAINING)
    private ResponseEntity<Training> addTraining(@RequestBody Training training){
        return ResponseEntity.of(Optional.of(trainingService.create(training)));
    }
}
