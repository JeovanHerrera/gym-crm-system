package com.jeovan.gymcrmsystem.helpers.validations;


import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.stereotype.Service;

@Service
public class TraineeValidator {

    public boolean checkForCreationSuccessful(Trainee trainee){
        return  checkFirstAndLastName(trainee.getUser().getFirstName(),trainee.getUser().getLastName());
    }
    public boolean checkForUpdateSuccessful(Trainee trainee){
        return checkFirstAndLastName(trainee.getUser().getFirstName(),trainee.getUser().getLastName()) && trainee.getUser().getIsActive() != null && trainee.getUser().getUsername() != null && !trainee.getUser().getUsername().isBlank();
    }

    boolean checkFirstAndLastName(String firstName, String lastName){
        return firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank();
    }
}
