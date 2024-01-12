package com.jeovan.gymcrmsystem.daos;

import com.jeovan.gymcrmsystem.models.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDao extends SimpleDao<User>{

    public UserDao(List<User> storage) {
        super(storage);
    }
}
