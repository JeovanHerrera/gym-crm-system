package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.daos.TraineeDao;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TraineeServiceImpl implements TraineeService {
    @Autowired
    private TraineeDao traineeDao;
    @Autowired
    private CredentialGeneratorServiceImpl credentialGeneratorService;
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
        user.setPassword(passwordEncoder.encode(credentialGeneratorService.generatePassword()));
        return traineeDao.save(trainee);
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
    @Secured("ADMIN")
    public Trainee selectByUsername(String username) {
        return traineeDao.findByUserUsername(username).get();
    }

    @Override
    @Secured("ADMIN")
    public Trainee updatePassword(String username){
        Trainee trainee = selectByUsername(username);
        User user = trainee.getUser();
        user.setPassword(passwordEncoder.encode(credentialGeneratorService.generatePassword()));
        return traineeDao.save(trainee);
    }

    @Override
    @Secured("ADMIN")
    public Trainee toggleActiveStatus(String username){
        Trainee trainee = selectByUsername(username);
        User user = trainee.getUser();
        if(user.getIsActive()){
            user.setIsActive(false);
        }else{
            user.setIsActive(true);
        }
        return traineeDao.save(trainee);
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

}
