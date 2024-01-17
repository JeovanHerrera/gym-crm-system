package com.jeovan.gymcrmsystem.services;

import java.util.List;
import java.util.UUID;

public interface SimpleService<T> {
    List<T> getAll();

    T create(T t);

    T update(T t);

    T select(UUID id);
    void delete(T t);
}
