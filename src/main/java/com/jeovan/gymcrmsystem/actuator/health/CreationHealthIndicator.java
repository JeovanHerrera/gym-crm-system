package com.jeovan.gymcrmsystem.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CreationHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        Health.Builder status = Health.up();
        return status.build();
    }
}
