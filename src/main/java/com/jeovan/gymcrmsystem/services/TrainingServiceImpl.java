package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainingDao;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TrainingServiceImpl implements TrainingService{

    @Autowired
    private InMemoryStorage inMemoryStorage;

    @Autowired
    private TrainingDao trainingDao;

    @Override
    public void setTrainingService() {
        trainingDao.setStorage((Map<UUID, Training>) inMemoryStorage.getStorage().get("Training"));
    }
    @Override
    public Map<UUID, Training> getAll() {
        return trainingDao.getAll(Training.class.getName());
    }

    @Override
    public Training create(Training training) {
        return trainingDao.save(training);
    }

    @Override
    public Training update(Training training) {
        return null;
    }

    @Override
    public Training select(UUID id) {
        return trainingDao.getById(id).get();
    }

    @Override
    public Training delete(Training training) {
        return null;
    }
}
