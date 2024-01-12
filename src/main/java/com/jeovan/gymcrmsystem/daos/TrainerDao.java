package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainer;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TrainerDao extends SimpleDao<Trainer> {

    public TrainerDao(List<Trainer> storage) {
        super(storage);
    }
}
