package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainingDao;
import com.jeovan.gymcrmsystem.exceptions.EntityNotFoundException;
import com.jeovan.gymcrmsystem.helpers.TrainingScheduleHelper;
import com.jeovan.gymcrmsystem.helpers.feing.ScheduledTrainingMicroserviceClient;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Training;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class TrainingServiceImpl implements TrainingService{

    @Autowired
    private TrainingDao trainingDao;
    @Autowired
    private TrainingTypeService trainingTypeService;
    @Autowired
    private TraineeService traineeService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private ScheduledTrainingMicroserviceClient scheduledTrainingMicroserviceClient;
    @Autowired
    private TrainingScheduleHelper trainingScheduleHelper;

    @Override
    public List<Training> getAll() {
        return trainingDao.findAll();
    }

    @Override
    @CircuitBreaker(name = "TrainingScheduleBreaker")
    public Training create(Training training) {
        training.setTrainingType(
                trainingTypeService.getByTrainingTypeName(training.getTrainingType().getTrainingTypeName())
                        .orElseThrow(() -> new RuntimeException("Training Type not found"))
        );
        training.setTrainer(
                trainerService.selectByUsername(training.getTrainer().getUser().getUsername())
                        .orElseThrow(() -> new EntityNotFoundException(Trainee.class.getSimpleName(), training.getTrainer().getUser().getUsername()))
        );
        training.setTrainee(
                traineeService.selectByUsername(training.getTrainee().getUser().getUsername())
                        .orElseThrow(() -> new EntityNotFoundException(Trainee.class.getSimpleName(), training.getTrainee().getUser().getUsername()))
        );

        scheduledTrainingMicroserviceClient.scheduleTraining(trainingScheduleHelper.setTrainingScheduleDetails(training, "ADD")).getStatusCode();
        log.info("Training added to microservice successfully");
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
