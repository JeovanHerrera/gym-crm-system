package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface UserDao extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
