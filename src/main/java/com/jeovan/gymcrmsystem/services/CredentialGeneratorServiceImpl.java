package com.jeovan.gymcrmsystem.services;

import com.jeovan.gymcrmsystem.constants.PasswordDefaults;
import com.jeovan.gymcrmsystem.daos.UserDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class CredentialGeneratorServiceImpl implements CredentialGeneratorService{

    private UserDao userDao;

    @Override
    public String generateUsername(@Valid String firstName, String lastName) {
        String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
        int suffix = 1;
        while(userDao.findByUsername(username).isPresent()){
            username = username + suffix;
            suffix++;
        }
        return username;
    }

    @Override
    public String generatePassword() {
        SecureRandom secureRandom = new SecureRandom();
        String password = "";
            password =
                    secureRandom
                            .ints(PasswordDefaults.ASCII_START, PasswordDefaults.ASCII_BOUND + 1)
                            .limit(PasswordDefaults.PASSWORD_LENGTH)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

        return password;
    }

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
}
