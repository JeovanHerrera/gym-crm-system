package com.jeovan.gymcrmsystem.helpers;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.CredentialGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TraineeBuilder {
    @Autowired
    private CredentialGeneratorService credentialGeneratorService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String buildTraineeForCreation(Trainee trainee) {
        User user = trainee.getUser();
        user.setUsername(credentialGeneratorService.generateUsername(user.getFirstName(), user.getLastName()));
        String password = credentialGeneratorService.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        return password;
    }

    public Trainee buildTraineeForUpdate(Trainee foundTrainee, Trainee traineeUpdates){
        foundTrainee.getUser().setFirstName(traineeUpdates.getUser().getFirstName());
        foundTrainee.getUser().setLastName(traineeUpdates.getUser().getLastName());
        foundTrainee.setAddress(traineeUpdates.getAddress().isEmpty() ? foundTrainee.getAddress() : traineeUpdates.getAddress());
        foundTrainee.setDateOfBirth(traineeUpdates.getDateOfBirth() == null ? foundTrainee.getDateOfBirth() : traineeUpdates.getDateOfBirth());
        return foundTrainee;
    }
}
