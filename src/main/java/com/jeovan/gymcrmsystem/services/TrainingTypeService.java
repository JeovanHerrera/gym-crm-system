package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.models.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeService {
    List<TrainingType> getAll();

    Optional<TrainingType> getByTrainingTypeName(String trainingTypeName);
}
