package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {
    @Test
    void createTrainerWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String trainingType = "Cardio Training";
        User user = new User();
        Trainer trainer = new Trainer(id, user, new ArrayList<>(), new ArrayList<>(), new TrainingType());
        assertEquals(id, trainer.getId());
        assertNotNull(trainer.getUser());
        assertNotNull(trainer.getTrainees());
        assertNotNull(trainer.getTrainings());
        assertNotNull(trainer.getTrainingType());
    }

    @Test
    void createTrainerWithNoArgsAndSetters() {
        Trainer trainer = new Trainer();
        UUID id = UUID.randomUUID();
        TrainingType trainingType = new TrainingType();
        User user = new User();

        trainer.setId(id);
        trainer.setTrainingType(trainingType);
        trainer.setUser(user);
        trainer.setTrainees(new ArrayList<>());
        trainer.setTrainings(new ArrayList<>());

        assertEquals(id, trainer.getId());
        assertNotNull(trainer.getUser());
        assertNotNull(trainer.getTrainees());
        assertNotNull(trainer.getTrainings());
        assertNotNull(trainer.getTrainingType());
    }
}