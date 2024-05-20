package com.jeovan.gymcrmsystem.handlers;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TrainingScheduleMicroserviceExceptionHandler {

    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void handleCallNotPermittedException() {
    }

    @ExceptionHandler({FeignException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFeignClientException(){
    }
}
