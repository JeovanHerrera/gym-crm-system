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
    @Query("SELECT t FROM Trainer t JOIN t.user u WHERE u.username = ?1")
    Optional<Trainer> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Trainer t WHERE EXISTS (SELECT 1 FROM User u WHERE u.username = ?1 AND u = t.user)")
    int deleteByUsername(String username);
}
