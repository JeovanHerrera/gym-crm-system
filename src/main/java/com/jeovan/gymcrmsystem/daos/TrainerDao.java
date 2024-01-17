package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface TrainerDao extends JpaRepository<Trainer, UUID> {
    @Query("SELECT t FROM Trainer t JOIN t.user u WHERE u.username = ?1")
    Optional<Trainer> findByUsername(String username);
}
