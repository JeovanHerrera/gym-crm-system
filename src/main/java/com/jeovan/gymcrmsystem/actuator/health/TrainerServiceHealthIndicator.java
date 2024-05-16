package com.jeovan.gymcrmsystem.actuator.health;

import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.services.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrainerServiceHealthIndicator implements HealthIndicator {
    private final TrainerService trainerService;

    @Override
    public Health health() {
        String DEFAULT_TRAINER = "juan.perez";
        Optional<Trainer> trainer = trainerService.selectByUsername(DEFAULT_TRAINER);
        Health.Builder status = Health.up();
        status.withDetail("default user", DEFAULT_TRAINER)
                .withDetail("is present", "si");
        if(trainer.isEmpty()){
            status.down();
            status.withDetail("default user", DEFAULT_TRAINER)
                    .withDetail("is present", "no");
        }
        return status.build();
    }
}
