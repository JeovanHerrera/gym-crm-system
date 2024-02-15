package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private TraineeDao traineeDao;
    @Autowired
    private CredentialGeneratorService credentialGeneratorService;
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Secured("ADMIN")
    public List<Trainee> getAll() {
        return traineeDao.findAll();
    }

    @Override
    public Trainee create(Trainee trainee) {
        User user = trainee.getUser();
        user.setUsername(credentialGeneratorService.generateUsername(user.getFirstName(), user.getLastName()));
        String password = credentialGeneratorService.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        trainee = traineeDao.save(trainee);
        trainee.getUser().setPassword(password);
        return trainee;
    }

    @Override
    @Secured("ADMIN")
    public Trainee update(Trainee trainee) {
        return traineeDao.save(trainee);
    }

    @Override
    @Secured("ADMIN")
    public Trainee select(UUID id) {
        return traineeDao.findById(id).get();
    }

    @Override
    //@Secured("ADMIN")
    public Optional<Trainee> selectByUsername(String username) {
        return traineeDao.findByUserUsername(username);
    }

    @Override
    //@Secured("ADMIN")
    public Trainee updatePassword(Credentials credentials){
        Optional<Trainee> trainee = selectByUsername(credentials.getUsername());
        if(trainee.isPresent()) {
            User user = trainee.get().getUser();
            if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(credentials.getNewPassword()));
                return traineeDao.save(trainee.get());
            }
        }
        return null;
    }

    @Override
    //@Secured("ADMIN")
    public Trainee toggleActiveStatus(User user){
        Optional<Trainee> trainee = selectByUsername(user.getUsername());
        if(trainee.isPresent()) {
            User userToUpdate = trainee.get().getUser();
            userToUpdate.setIsActive(!user.getIsActive());
            return traineeDao.save(trainee.get());
        }
        return null;
    }
    @Override
    @Secured("ADMIN")
    public void delete(Trainee trainee) {
        traineeDao.delete(trainee);
    }

    @Secured("ADMIN")
    public void deleteByUsername(String username) {
        traineeDao.deleteByUserUsername(username);
    }

    @Override
    public List<Trainer> updateTrainersList(String username, List<Trainer> trainers) {
        Optional<Trainee> foundTrainee = selectByUsername(username);
        if(foundTrainee.isPresent()){
            Trainee trainee = foundTrainee.get();
            trainers = trainers.stream().map(trainer -> trainerService.selectByUsername(trainer.getUser().getUsername())).filter(Optional::isPresent).map(Optional::get).toList();
            //trainee.setTrainers(trainers);
            traineeDao.save(trainee);
            return trainers;
        }
        return null;
    }
}
