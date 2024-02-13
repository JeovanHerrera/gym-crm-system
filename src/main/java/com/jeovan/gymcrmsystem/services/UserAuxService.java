package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;

public interface UserAuxService<T> {
    T selectByUsername(String username);
    void deleteByUsername(String username);
    T toggleActiveStatus(String username);
    T updatePassword(Credentials credentials);
}
