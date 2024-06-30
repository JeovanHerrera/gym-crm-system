package com.jeovan.gymcrmsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeovan.gymcrmsystem.configuration.SecurityConfig;
import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.security.*;
import com.jeovan.gymcrmsystem.services.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@ContextConfiguration(classes = {AuthenticationController.class, JwtDecoder.class, SecurityConfig.class, JwtAuthenticationFilter.class})
class AuthenticationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AuthenticationServiceImpl authenticationService;
    @MockBean
    private JwtIssuer jwtIssuer;
    @MockBean
    private JwtDecoder jwtDecoder;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private JwtToPrincipalConverter jwtToPrincipalConverter;

    @Test
    void Should_Login_When_UserIsValid() throws Exception {
        when(authenticationService.authenticateUser(any(), any())).thenReturn(new UsernamePasswordAuthenticationToken(new CustomUserDetail(User.builder().build()), "user2"));
        when(jwtIssuer.issue(any())).thenReturn("ffdaadsdsd");
        CredentialsDTO credentialsDTO = new CredentialsDTO("string.string", "password12", "");
        mockMvc.perform(post(EndPoint.LOGIN).content(objectMapper.writeValueAsString(credentialsDTO)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data-local.sql[0]").value("ffdaadsdsd"));
    }
}