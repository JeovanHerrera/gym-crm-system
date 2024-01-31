package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Training;

import java.util.List;
import java.util.Map;

public interface TrainingDaoCustom {
    List<Training> getByUsernameAndCriteria(String username, Map<String, String> criteria);
}
