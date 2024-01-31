package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeTest {

    @Test
    void createTrainingTypeWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String trainingTypeName = "Generic Type";
        TrainingType trainingType = new TrainingType(id, trainingTypeName);

        assertEquals(id, trainingType.getId());
        assertEquals(trainingTypeName, trainingType.getTrainingTypeName());
    }

    @Test
    void createTrainingTypeWithNoArgsConstructorAndSetters() {
        TrainingType trainingType = new TrainingType();
        UUID id = UUID.randomUUID();
        String trainingTypeName = "Generic Type";

        trainingType.setTrainingTypeName(trainingTypeName);
        trainingType.setId(id);

        assertEquals(id, trainingType.getId());
        assertEquals(trainingTypeName, trainingType.getTrainingTypeName());
    }
}