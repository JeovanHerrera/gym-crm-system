package com.jeovan.gymcrmsystem.services;

import org.springframework.security.core.Authentication;
public interface AuthenticationService {
    Authentication authenticateUser(String username, String password);
}
