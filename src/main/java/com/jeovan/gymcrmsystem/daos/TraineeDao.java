package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public interface TraineeDao extends JpaRepository<Trainee, UUID> {
    Optional<Trainee> findByUserUsername(String username);
    @Transactional
    void deleteByUserUsername(String username);
}
