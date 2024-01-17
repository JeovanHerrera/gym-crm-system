package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface TraineeDao extends JpaRepository<Trainee, UUID> {

    @Query("SELECT t FROM Trainee t JOIN t.user u WHERE u.username = ?1")
    Optional<Trainee> findByUsername(String username);
}
