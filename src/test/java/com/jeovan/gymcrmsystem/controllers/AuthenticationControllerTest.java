package com.jeovan.gymcrmsystem.controllers;

import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.security.JwtDecoder;
import com.jeovan.gymcrmsystem.security.JwtIssuer;
import com.jeovan.gymcrmsystem.security.JwtToPrincipalConverter;
import com.jeovan.gymcrmsystem.services.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AuthenticationServiceImpl authenticationService;
    @MockBean
    private JwtIssuer jwtIssuer;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private JwtToPrincipalConverter jwtToPrincipalConverter;

    @Test
    void Should_Login_When_UserIsValid() throws Exception {
        mockMvc.perform(get(EndPoint.LOGIN))
                .andExpect(status().isOk());
    }
}