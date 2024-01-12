package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TraineeDao extends SimpleDao<Trainee>{

    public TraineeDao(Map<UUID, Trainee> storage) {
        super(storage);
    }
}
