package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainerDao;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private InMemoryStorage inMemoryStorage;

    @Autowired
    private TrainerDao trainerDao;

    @Override
    public void setTrainerService() {
        trainerDao.setStorage((Map<UUID, Trainer>) inMemoryStorage.getStorage().get("Trainer"));
    }

    @Override
    public Map<UUID, Trainer> getAll() {
        return trainerDao.getAll(Trainer.class.getSimpleName());
    }

    @Override
    public Trainer create(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    @Override
    public Trainer update(Trainer trainer) {
        return trainerDao.update(trainer);
    }

    @Override
    public Trainer select(UUID id) {
        return trainerDao.getById(id).get();
    }

    @Override
    public Trainer delete(Trainer trainer) {
        return null;
    }
}
