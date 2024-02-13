package com.jeovan.gymcrmsystem.storage;

import com.jeovan.gymcrmsystem.models.SimpleInterface;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.Training;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
@Setter
public class InMemoryStorage {
private Map<String, Map<UUID, ? extends SimpleInterface>> storage;
    public InMemoryStorage() {
        this.storage = new HashMap<>();
        storage.put("Trainee", new HashMap<UUID, Trainee>());
        storage.put("Trainer", new HashMap<UUID, Trainer>());
        //storage.put("Training", new HashMap<UUID, Training>());
    }

}
