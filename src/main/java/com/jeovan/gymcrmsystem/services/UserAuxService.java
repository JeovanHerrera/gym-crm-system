package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.User;

import java.util.Optional;

public interface UserAuxService<T> {
    Optional<T> selectByUsername(String username);
    void deleteByUsername(String username);
    T toggleActiveStatus(User user);
    T updatePassword(CredentialsDTO credentialsDTO);
    String generateCredentials(User user);
}
