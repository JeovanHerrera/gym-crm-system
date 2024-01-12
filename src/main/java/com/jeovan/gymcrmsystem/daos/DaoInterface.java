package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.SimpleInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface DaoInterface<T> {
    Map<UUID, T> getAll(String entity);
    Optional<? extends SimpleInterface> getById(UUID id);
    T save(T t);
    T delete(T t);
    T update(T t);
}
