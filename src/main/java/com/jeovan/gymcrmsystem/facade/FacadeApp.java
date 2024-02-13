package com.jeovan.gymcrmsystem.facade;

import com.jeovan.gymcrmsystem.models.*;
import com.jeovan.gymcrmsystem.services.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class FacadeApp {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    private final AuthenticationService authenticationService;

    public FacadeApp(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService, AuthenticationService authenticationService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
        this.authenticationService = authenticationService;
    }

    public void run(){

        User user = new User();
        user.setFirstName("Jeovan");
        user.setLastName("Herrera");
        user.setIsActive(true);

        User userTrainer = new User();
        userTrainer.setFirstName("Juan");
        userTrainer.setLastName("Perez");
        userTrainer.setIsActive(true);

        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName("Cardio Training");

        Trainee trainee1 = new Trainee();
        trainee1.setDateOfBirth(Date.from(Instant.now()));
        trainee1.setAddress("Street 1st #2-34");
        trainee1.setUser(user);
        trainee1 = traineeService.create(trainee1);

        Trainer trainer1 = new Trainer();
        trainer1.setUser(userTrainer);
        trainer1.setTrainingType(trainingType);
        trainer1 = trainerService.create(trainer1);

        user.setFirstName("Jeovanis44444");
        trainee1.setUser(user);
        authenticationService.authenticateUser("jeovan.herrera", "b]yccvCdT?");
        traineeService.update(trainee1);

        Training training = new Training();
        training.setTrainee(trainee1);
        training.setTrainer(trainer1);
        training.setTrainingDate(Date.from(Instant.now()));
        training.setTrainingDuration(30L);
        training.setTrainingName("cardio");
        training.setTrainingType(trainingType);
        trainingService.create(training);

        traineeService.deleteByUsername("jeovan.herrera12345678910111213");
        System.out.println(traineeService.selectByUsername("jeovan.herrera").getDateOfBirth());

        Map<String, String> criteria = Map.of("trainingName", "cardio", "trainingDuration", "30");
        System.out.println(trainingService.getByUsernameAndCriteria("jeovan.herrera12345678910111213141516", criteria).toString());
    }
}
