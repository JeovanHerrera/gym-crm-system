package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Training;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainingDao extends SimpleDao<Training>{
    public TrainingDao(List<Training> storage) {
        super(storage);
    }
}
