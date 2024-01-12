package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.SimpleInterface;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Log4j2
public abstract class SimpleDao<T extends SimpleInterface> implements DaoInterface<T>{
    protected Map<UUID, T> storage;

    public SimpleDao(Map<UUID, T> storage) {
        this.storage = storage;
    }

    @Override
    public Map<UUID, T> getAll(String entity) {
        log.info("Getting all {} records", entity);
        return storage;
    }

    @Override
    public Optional<T> getById(UUID id){
        log.info("Getting record with id {}", id);
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public T save(T entity) {
        log.info("Starting saving for entity with id {}", entity.getId());
        storage.put(entity.getId(), entity);
        log.info("Saving for entity with id {} completed", entity.getId());
        return entity;
    }

    @Override
    public T delete(T entity) {
        log.info("Starting delete operation for entity with id {}", entity.getId());
        storage.remove(entity.getId());
        log.info("Delete operation for entity with id {} completed", entity.getId());
        return entity;
    }

    @Override
    public T update(T entity){
        log.info("Starting update operation for entity with id {}", entity.getId());
        Optional<? extends SimpleInterface> userToBeUpdated = getById(entity.getId());
        userToBeUpdated.ifPresent((entityToUpdate) -> storage.remove(entityToUpdate.getId()));
        storage.put(entity.getId(), entity);
        log.info("Update operation for entity with id {} completed", entity.getId());
        return entity;
    }

    public void setStorage(Map<UUID, T> storage) {
        this.storage = storage;
    }
}
