package com.jeovan.gymcrmsystem.storage;

import com.jeovan.gymcrmsystem.models.SimpleInterface;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.Training;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class InMemoryStorage {
    private Map<String, List<? extends SimpleInterface>> storage;
    public InMemoryStorage() {
        this.storage = new HashMap<>();
        storage.put("Trainee", new ArrayList<Trainee>());
        storage.put("Trainer", new ArrayList<Trainer>());
        storage.put("Training", new ArrayList<Training>());
    }

}
