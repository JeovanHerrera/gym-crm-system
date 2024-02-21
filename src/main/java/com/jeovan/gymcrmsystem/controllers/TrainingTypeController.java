package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.constants.SwaggerConstants;
import com.jeovan.gymcrmsystem.models.TrainingType;
import com.jeovan.gymcrmsystem.services.TrainingTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EndPoint.TRAINING_TYPE)
@AllArgsConstructor
public class TrainingTypeController {
    @Autowired
    private final TrainingTypeService trainingTypeService;

    @GetMapping
    @Operation(summary = SwaggerConstants.API_OPERATION_GET_ALL_TRAININGS_TYPES)
    private ResponseEntity<List<TrainingType>> getAllTrainingTypes(){
        return ResponseEntity.ok(trainingTypeService.getAll());
    }
}
