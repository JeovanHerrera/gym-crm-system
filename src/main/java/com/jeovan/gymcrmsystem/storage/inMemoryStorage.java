package com.jeovan.gymcrmsystem.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class inMemoryStorage{
    private Map<String, Map<String, Object>> inMemoryStorage;

    public inMemoryStorage() {
        this.inMemoryStorage = new HashMap<>();
    }
}
