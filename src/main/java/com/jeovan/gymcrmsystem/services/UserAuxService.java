package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.helpers.responses.Credentials;
import com.jeovan.gymcrmsystem.models.User;

import java.util.Optional;

public interface UserAuxService<T> {
    Optional<T> selectByUsername(String username);
    void deleteByUsername(String username);
    T toggleActiveStatus(User user);
    T updatePassword(Credentials credentials);
    String generateCredentials(User user);
}
