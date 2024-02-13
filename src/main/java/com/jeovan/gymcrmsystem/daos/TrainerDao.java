package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public interface TrainerDao extends JpaRepository<Trainer, UUID> {
    Optional<Trainer> findByUserUsername(String username);
    @Transactional
    int deleteByUserUsername(String username);
}
