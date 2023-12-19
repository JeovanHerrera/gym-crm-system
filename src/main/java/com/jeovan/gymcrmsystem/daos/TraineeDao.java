package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TraineeDao extends SimpleDao<Trainee>{

    public TraineeDao(List<Trainee> storage) {
        super(storage);
    }
}
