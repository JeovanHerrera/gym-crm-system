package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainerDao;
import com.jeovan.gymcrmsystem.models.Trainee;
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
        User user = trainer.getUser();
        user.setUsername(credentialGeneratorService.generateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(passwordEncoder.encode(credentialGeneratorService.generatePassword()));
        return trainerDao.save(trainer);
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

    @Secured("ADMIN")
    public Trainer selectByUsername(String username) {
        return trainerDao.findByUsername(username).get();
    }

    @Override
    public void deleteByUsername(String username) {
        trainerDao.deleteByUsername(username);
    }

    @Secured("ADMIN")
    public Trainer updatePassword(String username){
        Trainer trainer = selectByUsername(username);
        User user = trainer.getUser();
        user.setPassword(passwordEncoder.encode(credentialGeneratorService.generatePassword()));
        return trainerDao.save(trainer);
    }
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
