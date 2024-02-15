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
    private TrainingTypeService trainingTypeService;
    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TrainerService trainerService;


    @Override
    public List<Training> getAll() {
        return trainingDao.findAll();
    }

    @Override
    public Training create(Training training) {
        training.setTrainingType(trainingTypeService.getByTrainingTypeName(training.getTrainingType().getTrainingTypeName()).get());
        training.setTrainer(trainerService.selectByUsername(training.getTrainer().getUser().getUsername()).get());
        training.setTrainee(traineeService.selectByUsername(training.getTrainee().getUser().getUsername()).get());
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
