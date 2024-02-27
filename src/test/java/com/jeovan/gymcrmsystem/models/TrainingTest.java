package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {
    @Test
    void createTrainingWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Date trainingDate = Date.from(Instant.now());
        String trainingName = "new training";
        Trainer trainer = new Trainer();
        Trainee trainee = new Trainee();
        TrainingType trainingType = new TrainingType();
        Long duration = 10L;
        Training training = new Training(id, trainingDate, trainingName, trainee, trainer, trainingType, duration);
        assertEquals(id, training.getId());
        assertEquals(trainingDate, training.getTrainingDate());
        assertEquals(trainingName, training.getTrainingName());
        assertEquals(duration, training.getTrainingDuration());
        assertNotNull(training.getTrainee());
        assertNotNull(training.getTrainer());
        assertNotNull(training.getTrainingType());
    }

    @Test
    void createTrainingWithNoArgsAndSetters() {
        Training training = new Training();
        UUID id = UUID.randomUUID();
        Date trainingDate = Date.from(Instant.now());
        String trainingName = "new training";
        Trainer trainer = new Trainer();
        Trainee trainee = new Trainee();
        TrainingType trainingType = new TrainingType();
        Long duration = 10L;

        training.setId(id);
        training.setTrainingDate(trainingDate);
        training.setTrainingName(trainingName);
        training.setTrainer(trainer);
        training.setTrainee(trainee);
        training.setTrainingType(trainingType);
        training.setTrainingDuration(duration);

        assertEquals(id, training.getId());
        assertEquals(trainingDate, training.getTrainingDate());
        assertEquals(trainingName, training.getTrainingName());
        assertEquals(duration, training.getTrainingDuration());
        assertNotNull(training.getTrainee());
        assertNotNull(training.getTrainer());
        assertNotNull(training.getTrainingType());
    }
}