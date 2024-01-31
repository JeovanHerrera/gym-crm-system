package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.TrainingType;
import com.jeovan.gymcrmsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrainingTypeDao extends JpaRepository<TrainingType, UUID> {
    Optional<TrainingType> findByTrainingTypeName(String trainingTypeName);
}
