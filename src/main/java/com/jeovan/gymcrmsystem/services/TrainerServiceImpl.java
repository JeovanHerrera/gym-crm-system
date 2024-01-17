package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainerDao;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerDao trainerDao;

    @Autowired
    private CredentialGeneratorServiceImpl credentialGeneratorService;

    @Override
    public List<Trainer> getAll() {
        return trainerDao.findAll();
    }

    @Override
    public Trainer create(Trainer trainer) {
        User user = trainer.getUser();
        user.setUsername(credentialGeneratorService.generateUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(credentialGeneratorService.generatePassword());
        return trainerDao.save(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    @Override
    public Trainer select(UUID id) {
        return trainerDao.findById(id).get();
    }

    @Override
    public void delete(Trainer trainer) {
    }
}
