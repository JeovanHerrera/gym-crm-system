package com.jeovan.gymcrmsystem.helpers.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String type, String username){
        super(String.format("%s with username %s not found", type, username));
    }
}
