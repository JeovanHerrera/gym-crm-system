package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainingDao;
import com.jeovan.gymcrmsystem.daos.TrainingTypeDao;
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
    @Autowired
    private TrainingTypeDao trainingTypeDao;

    @Override
    public List<Training> getAll() {
        return trainingDao.findAll();
    }

    @Override
    public Training create(Training training) {
        training.setTrainingType(trainingTypeDao.findByTrainingTypeName(training.getTrainingType().getTrainingTypeName()).get());
        return trainingDao.save(training);
    }

    @Override
    public Training select(UUID id) {
        return trainingDao.findById(id).get();
    }

    @Override
    public List<Training> getByUsernameAndCriteria(String username, Map<String, String> criteria) {
        return trainingDao.getByUsernameAndCriteria(username, criteria);
    }
}
