package com.jeovan.gymcrmsystem.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUserWithAllArgsConstructor() {
        UUID id = UUID.randomUUID();
        String firstName = "Juan";
        String lastName = "Perez";
        String username = "juan.perez";
        String password = "password12";
        Boolean isActive = true;

        User user = new User(id, firstName, lastName, username, password, isActive);

        assertEquals(id, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(isActive, user.getIsActive());
    }

    @Test
    void createUserWithNoArgsConstructorAndSetters() {
        User user = new User();

        user.setId(UUID.randomUUID());
        user.setFirstName("Juan");
        user.setLastName("Perez");
        user.setUsername("juan.perez");
        user.setPassword("password12");
        user.setIsActive(false);

        assertNotNull(user.getId());
        assertEquals("Juan", user.getFirstName());
        assertEquals("Perez", user.getLastName());
        assertEquals("juan.perez", user.getUsername());
        assertEquals("password12", user.getPassword());
        assertEquals(false, user.getIsActive());
    }
}