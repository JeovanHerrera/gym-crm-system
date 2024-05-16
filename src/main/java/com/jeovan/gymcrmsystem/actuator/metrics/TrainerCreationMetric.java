package com.jeovan.gymcrmsystem.actuator.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class TrainerCreationMetric {
    private final Counter counter;

    public TrainerCreationMetric(MeterRegistry meterRegistry) {
        this.counter = meterRegistry.counter("trainerCreationEndpoint.hits", "Hits for POST method to /trainer", "");
    }
    public void increment() {
        counter.increment();
    }
}
