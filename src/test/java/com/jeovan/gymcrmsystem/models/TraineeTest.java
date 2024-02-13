package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TraineeTest {
    @Test
    void createTraineeWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        Date dateOfBirth = Date.from(Instant.now());
        String address = "Street 1st #2-34";
        User user = new User();
        Trainee trainee = new Trainee(id, dateOfBirth, address, user, new ArrayList<>(), new ArrayList<>());
        assertEquals(id, trainee.getId());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
        assertNotNull(trainee.getUser());
        assertNotNull(trainee.getTrainers());
        assertNotNull(trainee.getTrainings());
    }

    @Test
    void createTraineeWithNoArgsAndSetters() {
        Trainee trainee = new Trainee();
        UUID id = UUID.randomUUID();
        Date dateOfBirth = Date.from(Instant.now());
        String address = "Street 1st #2-34";
        User user = new User();

        trainee.setId(id);
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAddress(address);
        trainee.setUser(user);
        trainee.setTrainers(new ArrayList<>());
        trainee.setTrainings(new ArrayList<>());

        assertEquals(id, trainee.getId());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(address, trainee.getAddress());
        assertNotNull(trainee.getUser());
        assertNotNull(trainee.getTrainers());
        assertNotNull(trainee.getTrainings());
    }
}