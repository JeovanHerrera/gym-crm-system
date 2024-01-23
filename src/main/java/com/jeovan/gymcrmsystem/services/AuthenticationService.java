package com.jeovan.gymcrmsystem.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    void authenticateUser(String username, String password);
}
