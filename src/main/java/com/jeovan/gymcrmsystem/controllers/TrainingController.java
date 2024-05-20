package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.services.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(EndPoint.TRAINING)
@RequiredArgsConstructor
@Slf4j
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
        MDC.put("transaction.id", "tx-ID:" + UUID.randomUUID());
        log.info("Adding training, endpoint: " + EndPoint.TRAINING + " POST operation");
        ResponseEntity<Training> responseEntity = ResponseEntity.of(Optional.of(trainingService.create(training)));
        MDC.clear();
        return responseEntity;
    }
}
