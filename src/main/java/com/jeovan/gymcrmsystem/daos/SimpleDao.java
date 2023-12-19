package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.SimpleInterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class SimpleDao<T extends SimpleInterface> implements DaoInterface<T>{
    protected List<T> storage;

    public SimpleDao(List<T> storage) {
        this.storage = storage;
    }

    @Override
    public List<T> getAll(String entity) {
        return storage;
    }

    @Override
    public Optional<T> getById(UUID id){
        return Optional.ofNullable(storage.stream().filter(entity -> entity.getId().equals(id)).toList().get(0));
    }

    @Override
    public T save(T entity) {
        storage.add(entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        storage.remove(entity);
        return entity;
    }

    @Override
    public T update(T entity){
        Optional<? extends SimpleInterface> userToBeUpdated = getById(entity.getId());
        userToBeUpdated.ifPresent(storage::remove);
        storage.add(entity);
        return entity;
    }

    public void setStorage(List<T> storage) {
        this.storage = storage;
    }
}
