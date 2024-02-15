package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.models.TrainingType;
import com.jeovan.gymcrmsystem.services.TrainingService;
import com.jeovan.gymcrmsystem.services.TrainingTypeService;
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
    private ResponseEntity<List<TrainingType>> getAllTrainingTypes(){
        return ResponseEntity.ok(trainingTypeService.getAll());
    }


}
