package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private InMemoryStorage inMemoryStorage;

    @Autowired
    private TraineeDao traineeDao;

    public void setTraineeServiceImpl() {
        traineeDao.setStorage((List<Trainee>) inMemoryStorage.getStorage().get("Trainee"));
    }

    @Override
    public List<Trainee> getAll() {
        return traineeDao.getAll(Trainee.class.getName());
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
