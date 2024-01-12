package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.SimpleInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public abstract class SimpleDao<T extends SimpleInterface> implements DaoInterface<T>{
    protected Map<UUID, T> storage;

    public SimpleDao(Map<UUID, T> storage) {
        this.storage = storage;
    }

    @Override
    public Map<UUID, T> getAll(String entity) {
        return storage;
    }

    @Override
    public Optional<T> getById(UUID id){
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public T save(T entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        storage.remove(entity.getId());
        return entity;
    }

    @Override
    public T update(T entity){
        Optional<? extends SimpleInterface> userToBeUpdated = getById(entity.getId());
        userToBeUpdated.ifPresent((entityToUpdate) -> storage.remove(entityToUpdate.getId()));
        storage.put(entity.getId(), entity);
        return entity;
    }

    public void setStorage(Map<UUID, T> storage) {
        this.storage = storage;
    }
}
