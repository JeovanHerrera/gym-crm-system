package com.jeovan.gymcrmsystem.helpers;

import com.jeovan.gymcrmsystem.dtos.requests.TrainingDetailsDto;
import com.jeovan.gymcrmsystem.models.Training;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.stereotype.Component;

@Component
public class TrainingScheduleHelper {
    public TrainingDetailsDto setTrainingScheduleDetails(Training training, String action){
        TrainingDetailsDto trainingDetailsDto = new TrainingDetailsDto();
        User user = training.getTrainer().getUser();
        trainingDetailsDto.setUsername(user.getUsername());
        trainingDetailsDto.setFirstName(user.getFirstName());
        trainingDetailsDto.setLastName(user.getLastName());
        trainingDetailsDto.setIsActive(user.getIsActive());
        trainingDetailsDto.setTrainingDate(training.getTrainingDate()); // Sets current date.
        trainingDetailsDto.setDuration(training.getTrainingDuration());
        trainingDetailsDto.setActionType(action);
        return trainingDetailsDto;
    }
}
