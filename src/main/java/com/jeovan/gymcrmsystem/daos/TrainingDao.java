package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface TrainingDao  extends JpaRepository<Training, UUID>, TrainingDaoCustom {

}
