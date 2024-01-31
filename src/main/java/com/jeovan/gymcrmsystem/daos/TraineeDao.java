package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
