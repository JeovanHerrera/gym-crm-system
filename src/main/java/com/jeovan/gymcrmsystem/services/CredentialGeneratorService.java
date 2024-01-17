package com.jeovan.gymcrmsystem.services;

public interface CredentialGeneratorService {
    String generateUsername(String firstName, String lastName);
    String generatePassword();
}
