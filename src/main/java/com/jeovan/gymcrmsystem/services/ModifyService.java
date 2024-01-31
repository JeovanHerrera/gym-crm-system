package com.jeovan.gymcrmsystem.services;

public interface ModifyService<T> {
    T update(T t);
    void delete(T t);

}
