package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TrainerDao extends SimpleDao<Trainer> {

    public TrainerDao(Map<UUID, Trainer> storage) {
        super(storage);
    }
}
