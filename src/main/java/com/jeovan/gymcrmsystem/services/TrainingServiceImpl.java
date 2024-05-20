package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainingDao;
import com.jeovan.gymcrmsystem.dtos.requests.TrainingDetailsDto;
import com.jeovan.gymcrmsystem.exceptions.EntityNotFoundException;
import com.jeovan.gymcrmsystem.helpers.feing.ScheduledTrainingMicroserviceClient;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.models.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
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

        scheduledTrainingMicroserviceClient.scheduleTraining(setTrainingScheduleDetails(training)).getStatusCode();

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

    private TrainingDetailsDto setTrainingScheduleDetails(Training training){
        TrainingDetailsDto trainingDetailsDto = new TrainingDetailsDto();
        User user = training.getTrainer().getUser();
        trainingDetailsDto.setUsername(user.getUsername());
        trainingDetailsDto.setFirstName(user.getFirstName());
        trainingDetailsDto.setLastName(user.getLastName());
        trainingDetailsDto.setIsActive(user.getIsActive());
        trainingDetailsDto.setTrainingDate(training.getTrainingDate()); // Sets current date.
        trainingDetailsDto.setDuration(training.getTrainingDuration());
        trainingDetailsDto.setActionType("ADD");
        return trainingDetailsDto;
    }

}
