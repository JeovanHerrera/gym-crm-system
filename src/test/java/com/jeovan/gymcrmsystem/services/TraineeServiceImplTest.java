package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.helpers.TraineeBuilder;
import com.jeovan.gymcrmsystem.exceptions.InvalidEntityException;
import com.jeovan.gymcrmsystem.helpers.validations.TraineeValidator;
import com.jeovan.gymcrmsystem.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TraineeServiceImplTest {

    private static final UUID UUID1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
    private static final UUID UUID2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
    private static final String FIRST_NAME = "Juan";
    private static final String LAST_NAME = "Perez";
    private static final String USERNAME = "juan.perez";
    private static final String PASSWORD = "asdfghjklo";
    private static final boolean IS_ACTIVE = true;
    private static final User USER = new User(UUID2,FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, IS_ACTIVE);

    private static final Trainee TRAINEE_FOR_CREATION = new Trainee();
    private static final User USER_FOR_CREATION = new User();
    private static final Trainee TRAINEE1 = new Trainee(UUID1, Date.from(Instant.now()), "Street 1st #2-34", USER, new ArrayList<>(), new ArrayList<>());

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
        USER_FOR_CREATION.setFirstName(FIRST_NAME);
        USER_FOR_CREATION.setLastName(LAST_NAME);
        USER_FOR_CREATION.setIsActive(true);
        TRAINEE_FOR_CREATION.setAddress("Street 1st #2-34");
        TRAINEE_FOR_CREATION.setUser(USER_FOR_CREATION);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void Should_ReturnANewTrainee_When_ValidationSucceeds() {
        when(traineeValidator.checkForCreationSuccessful(TRAINEE_FOR_CREATION)).thenReturn(true);
        when(mockDao.save(any(Trainee.class))).thenReturn(TRAINEE1);
        assertEquals(TRAINEE1, service.create(TRAINEE_FOR_CREATION));
    }

    @Test
    void Should_ThrowAnInvalidEntityException_When_ValidationFails() {
        when(traineeValidator.checkForCreationSuccessful(TRAINEE_FOR_CREATION)).thenReturn(false);
        when(mockDao.save(any(Trainee.class))).thenReturn(TRAINEE1);
        assertThrows(InvalidEntityException.class, () -> service.create(TRAINEE_FOR_CREATION));
    }

}