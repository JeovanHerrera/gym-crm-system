package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TrainingTypeDao;
import com.jeovan.gymcrmsystem.models.TrainingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingTypeServiceImpl implements TrainingTypeService{

    @Autowired
    private TrainingTypeDao trainingTypeDao;

    @Override
    public List<TrainingType> getAll() {
        return trainingTypeDao.findAll();
    }

    @Override
    public Optional<TrainingType> getByTrainingTypeName(String trainingTypeName) {
        return trainingTypeDao.findByTrainingTypeName(trainingTypeName);
    }
}
