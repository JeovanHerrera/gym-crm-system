package com.jeovan.gymcrmsystem.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SimpleService<T> {
    Map<UUID, T> getAll();

    T create(T t);

    T update(T t);

    T select(UUID id);
    T delete(T t);
}
