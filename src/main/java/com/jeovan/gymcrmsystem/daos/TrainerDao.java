package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class TrainerDao extends SimpleDao<Trainer> {

    public TrainerDao(List<Trainer> storage) {
        super(storage);
    }
}
