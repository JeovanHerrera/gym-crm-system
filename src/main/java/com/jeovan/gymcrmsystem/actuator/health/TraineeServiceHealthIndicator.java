package com.jeovan.gymcrmsystem.actuator.health;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.services.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TraineeServiceHealthIndicator implements HealthIndicator {

    private final TraineeService traineeService;

    @Override
    public Health health() {
        Optional<Trainee> trainee = traineeService.selectByUsername("jeovan.herrera");
        Health.Builder status = Health.up();
        if(trainee.isEmpty()){
            status.down();
        }
        return status.build();
    }
}
