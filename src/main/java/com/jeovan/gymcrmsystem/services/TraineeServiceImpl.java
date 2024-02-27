package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.helpers.TraineeBuilder;
import com.jeovan.gymcrmsystem.exceptions.EntityNotFoundException;
import com.jeovan.gymcrmsystem.exceptions.InvalidEntityException;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.helpers.validations.TraineeValidator;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TraineeValidator traineeValidator;
    @Autowired
    private TraineeBuilder traineeBuilder;

    @Override
    public List<Trainee> getAll() {
        return traineeDao.findAll();
    }

    @Override
    public Trainee create(Trainee trainee) {
        if(traineeValidator.checkForCreationSuccessful(trainee)){
            String password = traineeBuilder.buildTraineeForCreation(trainee);
            Trainee persistedTrainee = traineeDao.save(trainee);
            persistedTrainee.getUser().setPassword(password);
            return persistedTrainee;
        }
        throw new InvalidEntityException(Trainee.class.getSimpleName());
    }

    @Override
    public Trainee update(Trainee trainee) {
        if(traineeValidator.checkForUpdateSuccessful(trainee)){
            Optional<Trainee> foundTrainee = traineeDao.findByUserUsername(trainee.getUser().getUsername());
            if(foundTrainee.isPresent()){
                return traineeDao.save(traineeBuilder.buildTraineeForUpdate(foundTrainee.get(), trainee));
            }
            throw new EntityNotFoundException(Trainee.class.getSimpleName(), trainee.getUser().getUsername());
        }
        throw new InvalidEntityException(Trainee.class.getSimpleName());
    }

    @Override
    public Trainee select(UUID id) {
        return traineeDao.findById(id).orElse(null);
    }

    @Override
    public Optional<Trainee> selectByUsername(String username) {
        return traineeDao.findByUserUsername(username);
    }

    @Override
    public Trainee updatePassword(CredentialsDTO credentialsDTO){
        Optional<Trainee> trainee = selectByUsername(credentialsDTO.getUsername());
        if(trainee.isPresent()) {
            User user = trainee.get().getUser();
            if (passwordEncoder.matches(credentialsDTO.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(credentialsDTO.getNewPassword()));
                return traineeDao.save(trainee.get());
            }
        }
        return null;
    }

    @Override
    public String generateCredentials(User user) {
        return null;
    }

    @Override
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
    public void delete(Trainee trainee) {
        traineeDao.delete(trainee);
    }

    public void deleteByUsername(String username) {
        traineeDao.deleteByUserUsername(username);
    }

    @Override
    public List<Trainer> updateTrainersList(String username, List<Trainer> trainers) {
        Optional<Trainee> foundTrainee = selectByUsername(username);
        if(foundTrainee.isPresent()){
            Trainee trainee = foundTrainee.get();
            List<Trainer> currentTrainers = trainee.getTrainers();
            currentTrainers.addAll(trainers.stream().map(trainer -> trainerService.selectByUsername(trainer.getUser().getUsername())).filter(trainer -> trainer.isPresent() && !currentTrainers.contains(trainer.get()) ).map(Optional::get).toList());
            traineeDao.save(trainee);
            return trainee.getTrainers();
        }
        return null;
    }
}
