package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.services.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoint.TRAINING)
@AllArgsConstructor
public class TrainingController {
    @Autowired
    private final TrainingService trainingService;

    @GetMapping
    private ResponseEntity<List<Training>> getAllTrainings(){
        return ResponseEntity.ok(trainingService.getAll());
    }

    @PostMapping
    private ResponseEntity<Training> addTraining(@RequestBody Training training){
        return ResponseEntity.of(Optional.of(trainingService.create(training)));
    }
}
