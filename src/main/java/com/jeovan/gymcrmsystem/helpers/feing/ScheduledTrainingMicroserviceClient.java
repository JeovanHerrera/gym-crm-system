package com.jeovan.gymcrmsystem.helpers.feing;

import com.jeovan.gymcrmsystem.dtos.requests.TrainingDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "training-microservice")
public interface ScheduledTrainingMicroserviceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/schedule",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> scheduleTraining(TrainingDetailsDto trainingDetailsDto);

    @RequestMapping(method = RequestMethod.GET, value = "/schedule" + "/{username}/duration",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Double> getTrainerDurationByMonth(@PathVariable("username") String username,
                                                     @RequestParam("yearMonth") String yearMonth);

    @RequestMapping(method = RequestMethod.GET, value = "/schedule")
    String hello();
}
