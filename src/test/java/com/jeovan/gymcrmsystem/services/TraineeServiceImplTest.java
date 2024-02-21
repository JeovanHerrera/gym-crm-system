package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.helpers.TraineeBuilder;
import com.jeovan.gymcrmsystem.helpers.exceptions.InvalidEntityException;
import com.jeovan.gymcrmsystem.helpers.validations.TraineeValidator;
import com.jeovan.gymcrmsystem.models.*;
import com.jeovan.gymcrmsystem.storage.InMemoryStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TraineeServiceImplTest {

    private static final UUID uuid1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
    private static final UUID uuid2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
    private static final String firstName = "Juan";
    private static final String lastName = "Perez";
    private static final String username = "juan.perez";
    private static final String password = "asdfghjklo";
    private static final boolean isActive = true;
    private static final User user = new User(uuid2,firstName, lastName, username, password, isActive);

    private static final Trainee traineeForCreation = new Trainee();
    private static final User userForCreation = new User();
    private static final Trainee trainee1 = new Trainee(uuid2, Date.from(Instant.now()), "Street 1st #2-34", user, new ArrayList<>(), new ArrayList<>());

    public Map<String, Map<UUID, ? extends SimpleInterface>> storage = new HashMap<>();

    @Mock
    private CredentialGeneratorService credentialGeneratorService;
    @Mock
    private TrainerService trainerService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TraineeValidator traineeValidator;
    @Mock
    private TraineeBuilder traineeBuilder;
    @Mock
    TraineeDao mockDao;
    @InjectMocks
    TraineeServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userForCreation.setFirstName(firstName);
        userForCreation.setLastName(lastName);
        userForCreation.setIsActive(true);
        traineeForCreation.setAddress("Street 1st #2-34");
        traineeForCreation.setUser(userForCreation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldReturnANewTraineeWithUsernameAndPassword() {
        when(traineeValidator.checkForCreationSuccessful(traineeForCreation)).thenReturn(true);
        when(mockDao.save(any(Trainee.class))).thenReturn(trainee1);
        assertEquals(trainee1, service.create(traineeForCreation));
    }

    @Test
    void shouldThrowAnInvalidEntityExceptionWhenValidationFails() {
        when(traineeValidator.checkForCreationSuccessful(traineeForCreation)).thenReturn(false);
        when(mockDao.save(any(Trainee.class))).thenReturn(trainee1);
        assertThrows(InvalidEntityException.class, () -> service.create(traineeForCreation));
    }
/*
    @Test
    void testUpdate() {
        when(mockDao.update(any(Trainee.class))).thenReturn(trainee1);
        assertEquals(trainee1, service.update(trainee1));
    }

    @Test
    void select() {
        when(mockDao.getById(any(UUID.class))).thenReturn(Optional.of(trainee1));
        assertEquals(trainee1, service.select(uuid2));
    }

    @Test
    void delete() {
        when(mockDao.delete(any(Trainee.class))).thenReturn(trainee1);
        assertEquals(trainee1, service.delete(trainee1));
    }*/

}