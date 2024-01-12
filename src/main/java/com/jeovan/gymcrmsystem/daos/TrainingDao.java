package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Training;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TrainingDao extends SimpleDao<Training>{
    public TrainingDao(Map<UUID, Training> storage) {
        super(storage);
    }
}
