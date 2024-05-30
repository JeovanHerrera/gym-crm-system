package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.constants.MessagingQueues;
import com.jeovan.gymcrmsystem.constants.ScheduledTrainingActions;
import com.jeovan.gymcrmsystem.daos.TrainingDao;
import com.jeovan.gymcrmsystem.exceptions.EntityNotFoundException;
import com.jeovan.gymcrmsystem.helpers.TrainingScheduleHelper;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.models.TrainingType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService{

    private final TrainingDao trainingDao;
    private final TrainingTypeService trainingTypeService;
    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingScheduleHelper trainingScheduleHelper;
    private final JmsTemplate jmsTemplate;

    @Override
    public List<Training> getAll() {
        return trainingDao.findAll();
    }

    @Override
    @CircuitBreaker(name = "TrainingScheduleBreaker")
    public Training create(Training training) {
        String trainerUsername = training.getTrainer().getUser().getUsername();
        String traineeUsername = training.getTrainee().getUser().getUsername();
        training.setTrainingType(
                trainingTypeService.getByTrainingTypeName(training.getTrainingType().getTrainingTypeName())
                        .orElseThrow(() -> new EntityNotFoundException(TrainingType.class.getSimpleName()))
        );
        training.setTrainer(
                trainerService.selectByUsername(trainerUsername)
                        .orElseThrow(() -> new EntityNotFoundException(Trainer.class.getSimpleName(), trainerUsername))
        );
        training.setTrainee(
                traineeService.selectByUsername(traineeUsername)
                        .orElseThrow(() -> new EntityNotFoundException(Trainee.class.getSimpleName(), traineeUsername))
        );
        jmsTemplate.convertAndSend(MessagingQueues.SCHEDULED_TRAINING_QUEUE, trainingScheduleHelper.setTrainingScheduleDetails(training, ScheduledTrainingActions.ADD));
        log.info("Training added to microservice successfully");
        return trainingDao.save(training);
    }

    @Override
    public Training select(UUID id) {
        return trainingDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Training.class.getSimpleName()));
    }

    @Override
    public List<Training> getByUsernameAndCriteria(String username, Map<String, String> criteria) {
        return trainingDao.getByUsernameAndCriteria(username, criteria);
    }
}
