package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public interface TraineeDao extends JpaRepository<Trainee, UUID> {

    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.username = ?1")
    Optional<Trainee> findByUsername(String username);
    @Modifying
    @Transactional
    @Query("DELETE FROM Trainee t WHERE EXISTS (SELECT 1 FROM User u WHERE u.username = ?1 AND u = t.user)")
    int deleteByUsername(String username);
}
