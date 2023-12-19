package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.models.Trainee;

import java.util.List;
import java.util.UUID;

public interface TraineeService {
    List<Trainee> getAll();

    Trainee create(Trainee trainee);

    Trainee update(Trainee trainee);

    Trainee select(UUID id);

    Trainee delete(Trainee trainee);

}
