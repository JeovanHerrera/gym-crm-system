package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.models.Training;

import java.util.List;
import java.util.Map;

public interface TrainingService extends SimpleService<Training> {
    List<Training> getByUsernameAndCriteria(String username, Map<String, String> criteria);
}
