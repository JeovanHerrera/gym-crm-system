package com.jeovan.gymcrmsystem.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeovan.gymcrmsystem.models.SimpleInterface;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.Training;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class DataLoading implements BeanPostProcessor {
    private ObjectMapper objectMapper;
    private final String dataFilePath;

    private Map<String, List<? extends SimpleInterface>> storageMap;

    public DataLoading(@Value("${data.file.path}") String dataFilePath) {
        this.dataFilePath = dataFilePath;
        this.objectMapper = new ObjectMapper();
        this.storageMap = new HashMap<>();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanCreationException {
        if (bean.getClass().equals(InMemoryStorage.class)) {
            try {
                Map<String,List<Object>> dataList = objectMapper.readValue(new File(dataFilePath), new TypeReference<Map<String,List<Object>>>() {});
                List<Trainer> trainers = objectMapper.convertValue(dataList.get("Trainer"), new TypeReference<List<Trainer>>() {});
                List<Trainee> trainees = objectMapper.convertValue(dataList.get("Trainee"), new TypeReference<List<Trainee>>() {});
                List<Training> trainings = objectMapper.convertValue(dataList.get("Training"), new TypeReference<List<Training>>() {});
                populateStorage(trainers, "Trainer");
                populateStorage(trainees, "Trainee");
                populateStorage(trainings, "Training");
                ((InMemoryStorage) bean).setStorage(storageMap);
            } catch (Exception e) {
                throw new BeanCreationException("Error initializing storage with data", e);
            }
        }
        return bean;
    }

    private void populateStorage(List<? extends SimpleInterface> entities, String namespace) {
        storageMap.put(namespace, entities);

    }
}
