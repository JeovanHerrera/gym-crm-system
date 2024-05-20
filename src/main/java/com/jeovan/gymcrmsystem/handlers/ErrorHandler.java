package com.jeovan.gymcrmsystem.handlers;

import com.jeovan.gymcrmsystem.dtos.responses.ErrorHandlerDTO;
import com.jeovan.gymcrmsystem.exceptions.EntityNotFoundException;
import com.jeovan.gymcrmsystem.exceptions.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorHandlerDTO> handleEntityNoFoundException(EntityNotFoundException exception){
        ErrorHandlerDTO errorHandlerDTO = ErrorHandlerDTO.builder().type(exception.getMessage()).build();
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorHandlerDTO);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorHandlerDTO> handleInvalidEntityException(InvalidEntityException exception){
        ErrorHandlerDTO errorHandlerDTO = ErrorHandlerDTO.builder().type(exception.getMessage()).build();
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorHandlerDTO);
    }
}
