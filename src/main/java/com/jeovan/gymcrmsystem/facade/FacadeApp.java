package com.jeovan.gymcrmsystem.facade;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

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

       final UUID uuid1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
        final UUID uuid2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
        User user = new User();
        user.setFirstName("Jeovan");
        user.setLastName("Herrera");
        user.setUsername("jeovanis.herrera");
        user.setIsActive(true);
        Trainee trainee1 = new Trainee();
        trainee1.setDateOfBirth(Date.from(Instant.now()));
        trainee1.setAddress("Street 1st #2-34");
        trainee1.setUser(user);
        trainee1 = traineeService.create(trainee1);
        user.setFirstName("Jeovanis44444");
        trainee1.setUser(user);
        authenticationService.authenticateUser("jeovan.herrera1", "sBNyw;Xro`");
        traineeService.update(trainee1);
        traineeService.deleteByUsername("jeovan.herrera12345");
        //System.out.println(traineeService.selectByUsername("jeovan.herrera").toString());
    }
}
