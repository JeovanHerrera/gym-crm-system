package com.jeovan.gymcrmsystem.exceptions;

public class InvalidEntityException extends RuntimeException{
    public InvalidEntityException(String type){
        super(String.format("%s has missing or invalid required arguments", type));
    }
}
