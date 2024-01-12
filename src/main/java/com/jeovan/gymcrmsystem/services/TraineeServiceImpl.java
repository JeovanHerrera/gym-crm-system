package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private InMemoryStorage inMemoryStorage;

    @Autowired
    private TraineeDao traineeDao;

    @Override
    public void setTraineeService() {
        traineeDao.setStorage((Map<UUID, Trainee>) inMemoryStorage.getStorage().get("Trainee"));
    }

    @Override
    public Map<UUID, Trainee> getAll() {
        return traineeDao.getAll(Trainee.class.getSimpleName());
    }

    @Override
    public Trainee create(Trainee trainee) {
        return traineeDao.save(trainee);
    }

    @Override
    public Trainee update(Trainee trainee) {
        return traineeDao.update(trainee);
    }

    @Override
    public Trainee select(UUID id) {
        return traineeDao.getById(id).get();
    }

    @Override
    public Trainee delete(Trainee trainee) {
        return traineeDao.delete(trainee);
    }
}
