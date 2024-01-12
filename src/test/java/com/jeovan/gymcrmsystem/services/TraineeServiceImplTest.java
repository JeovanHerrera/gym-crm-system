package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
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

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TraineeServiceImplTest {

    private static final UUID uuid1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
    private static final UUID uuid2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
    private static final Trainee trainee1 = new Trainee(uuid2, Date.from(Instant.now()), "Street 1st #2-34", uuid1);

    public Map<String, Map<UUID, ? extends SimpleInterface>> storage = new HashMap<>();

    InMemoryStorage inMemoryStorage = mock(InMemoryStorage.class);
    @Mock
    TraineeDao mockDao;
    @InjectMocks
    TraineeServiceImpl service = new TraineeServiceImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service.setTraineeService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAll() {
        Map<UUID, Trainee> expectedTrainees = Map.of(uuid1, new Trainee(), uuid2, new Trainee());
        when(mockDao.getAll(any(String.class))).thenReturn(expectedTrainees);
        assertEquals(expectedTrainees, service.getAll());
    }

    @Test
    void testCreate() {
        when(mockDao.save(any(Trainee.class))).thenReturn(trainee1);
        assertEquals(trainee1, service.create(trainee1));
    }

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
    }

}