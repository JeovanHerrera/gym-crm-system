package com.jeovan.gymcrmsystem.actuator.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class TraineeCreationMetric {
    private final Counter counter;

    public TraineeCreationMetric(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("traineeCreationEndpoint.hits", "Hits for POST method to /trainee", "");
    }
    public void increment() {
        counter.increment();
    }
}
