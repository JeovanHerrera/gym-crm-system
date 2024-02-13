package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainerDao;
import com.jeovan.gymcrmsystem.daos.TrainingTypeDao;
import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerDao trainerDao;
    @Autowired
    private TrainingTypeDao trainingTypeDao;
    @Autowired
    private CredentialGeneratorServiceImpl credentialGeneratorService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Secured("ADMIN")
    public List<Trainer> getAll() {
        return trainerDao.findAll();
    }

    @Override
    public Trainer create(Trainer trainer) {
        trainer.setTrainingType(trainingTypeDao.findByTrainingTypeName(trainer.getTrainingType().getTrainingTypeName()).get());
        User user = trainer.getUser();
        user.setUsername(credentialGeneratorService.generateUsername(user.getFirstName(), user.getLastName()));
        String password = credentialGeneratorService.generatePassword();
        user.setPassword(passwordEncoder.encode(password));
        trainer = trainerDao.save(trainer);
        trainer.getUser().setPassword(password);
        return trainer;
    }

    @Override
    @Secured("ADMIN")
    public Trainer update(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    @Override
    @Secured("ADMIN")
    public Trainer select(UUID id) {
        return trainerDao.findById(id).get();
    }

    @Override
    @Secured("ADMIN")
    public Trainer selectByUsername(String username) {
        return trainerDao.findByUserUsername(username).get();
    }

    @Override
    @Secured("ADMIN")
    public void deleteByUsername(String username) {
        trainerDao.deleteByUserUsername(username);
    }

    @Override
    @Secured("ADMIN")
    public Trainer updatePassword(Credentials credentials){
        Trainer trainer = selectByUsername(credentials.getUsername());
        User user = trainer.getUser();
        user.setPassword(passwordEncoder.encode(credentialGeneratorService.generatePassword()));
        return trainerDao.save(trainer);
    }
    @Override
    @Secured("ADMIN")
    public Trainer toggleActiveStatus(String username){
        Trainer trainer = selectByUsername(username);
        User user = trainer.getUser();
        if(user.getIsActive()){
            user.setIsActive(false);
        }else{
            user.setIsActive(true);
        }
        return trainerDao.save(trainer);
    }
    @Override
    @Secured("ADMIN")
    public void delete(Trainer trainer) {
    }
}
