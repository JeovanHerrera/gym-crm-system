package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;

import java.util.List;

public interface TraineeService extends SimpleService<Trainee>, ModifyService<Trainee>, UserAuxService<Trainee> {
    List<Trainer> updateTrainersList(String username, List<Trainer> trainers);
}
