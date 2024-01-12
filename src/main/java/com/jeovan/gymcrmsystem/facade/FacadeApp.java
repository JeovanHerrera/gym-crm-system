package com.jeovan.gymcrmsystem.facade;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class FacadeApp {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    public FacadeApp(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void run(){
        traineeService.setTraineeService();
        trainerService.setTrainerService();
        trainingService.setTrainingService();

        final UUID uuid1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
        final UUID uuid2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
        final Trainee trainee1 = new Trainee(uuid2, Date.from(Instant.now()), "Street 1st #2-34", uuid1);
        traineeService.create(trainee1);
        Trainee trainee2 = traineeService.select(UUID.fromString("1906d765-0df2-4c7b-8225-02f067830216"));
        trainee2.setAddress("calle 4 3-33");
        trainee2 = traineeService.update(trainee2);
        System.out.println(trainee2.toString());
        System.out.println(traineeService.getAll().toString());
        Trainee trainee3 = traineeService.delete(trainee2);
        System.out.println(traineeService.getAll().toString());
        System.out.println(trainerService.getAll().toString());
        System.out.println(trainingService.getAll().toString());
    }
}
