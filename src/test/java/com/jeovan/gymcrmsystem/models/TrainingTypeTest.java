package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeTest {

    @Test
    void createTrainingTypeWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String trainingTypeName = "Generic Type";
        List<Trainer> trainers = new ArrayList<>();
        TrainingType trainingType = new TrainingType(id, trainingTypeName, trainers);

        assertEquals(id, trainingType.getId());
        assertEquals(trainingTypeName, trainingType.getTrainingTypeName());
        assertNotNull(trainingType.getTrainers());
    }

    @Test
    void createTrainingTypeWithNoArgsConstructorAndSetters() {
        TrainingType trainingType = new TrainingType();
        UUID id = UUID.randomUUID();
        String trainingTypeName = "Generic Type";

        trainingType.setTrainingTypeName(trainingTypeName);
        trainingType.setId(id);
        trainingType.setTrainers(new ArrayList<>());

        assertEquals(id, trainingType.getId());
        assertEquals(trainingTypeName, trainingType.getTrainingTypeName());
        assertNotNull(trainingType.getTrainers());
    }
}