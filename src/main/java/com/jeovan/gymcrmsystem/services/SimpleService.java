package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.models.Trainee;

import java.util.List;
import java.util.UUID;

public interface SimpleService<T> {
    List<T> getAll();

    T create(T t);

    T update(T t);

    T select(UUID id);
    void delete(T t);
    T selectByUsername(String username);
    void deleteByUsername(String username);
}
