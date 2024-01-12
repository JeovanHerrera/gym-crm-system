package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class UserDao extends SimpleDao<User>{

    public UserDao(Map<UUID, User> storage) {
        super(storage);
    }
}
