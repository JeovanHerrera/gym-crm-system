package com.jeovan.gymcrmsystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeovan.gymcrmsystem.actuator.metrics.TraineeCreationMetric;
import com.jeovan.gymcrmsystem.configuration.SecurityConfig;
import com.jeovan.gymcrmsystem.constants.EndPoint;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.Trainer;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.security.*;
import com.jeovan.gymcrmsystem.services.TraineeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TraineeController.class)
@ContextConfiguration(classes = {TraineeController.class, JwtDecoder.class, SecurityConfig.class, JwtAuthenticationFilter.class})
public class TraineeControllerTest {

    private static final UUID UUID1 = UUID.fromString("983ffb47-5118-40df-b38b-a6604b44555d");
    private static final UUID UUID2 = UUID.fromString("5ab5de9f-dcc2-4677-9457-c63686236f97");
    private static final String FIRST_NAME = "Juan";
    private static final String LAST_NAME = "Perez";
    private static final String USERNAME = "juan.perez";
    private static final String PASSWORD = "asdfghjklo";
    private static final boolean IS_ACTIVE = true;
    private static final User USER = new User(UUID2,FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, IS_ACTIVE);

    private static final Trainee TRAINEE_FOR_CREATION = new Trainee();
    private static final User USER_FOR_CREATION = new User();
    private static final Trainee TRAINEE1 = new Trainee(UUID1, Date.from(Instant.now()), "Street 1st #2-34", USER, new ArrayList<>(), new ArrayList<>());

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private TraineeServiceImpl traineeService;
    @MockBean
    private TraineeCreationMetric traineeCreationMetric;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private JwtToPrincipalConverter jwtToPrincipalConverter;

    @BeforeEach
    void setUp() {
        USER_FOR_CREATION.setFirstName(FIRST_NAME);
        USER_FOR_CREATION.setLastName(LAST_NAME);
        USER_FOR_CREATION.setIsActive(true);
        TRAINEE_FOR_CREATION.setAddress("Street 1st #2-34");
        TRAINEE_FOR_CREATION.setUser(USER_FOR_CREATION);
    }

    @Test
    void Should_CreateNewUser_When_TraineeIsValid() throws Exception {
        when(traineeService.create(any(Trainee.class))).thenReturn(TRAINEE1);
        mockMvc.perform(post(EndPoint.TRAINEE).content(objectMapper.writeValueAsString(TRAINEE_FOR_CREATION)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("juan.perez"));
    }

    @Test
    void Should_ReturnBadRequest_When_TraineeIsInvalid() throws Exception {
        USER_FOR_CREATION.setFirstName(null);
        TRAINEE_FOR_CREATION.setUser(USER_FOR_CREATION);
        when(traineeService.create(any(Trainee.class))).thenReturn(TRAINEE1);
        mockMvc.perform(post(EndPoint.TRAINEE).content(objectMapper.writeValueAsString(TRAINEE_FOR_CREATION)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void Should_ReturnOK_When_PasswordIsUpdatedSuccessfully() throws Exception {
        CredentialsDTO credentialsDTO = new CredentialsDTO();

        when(traineeService.updatePassword(any(CredentialsDTO.class))).thenReturn(TRAINEE1);

        mockMvc.perform(put(EndPoint.TRAINEE + EndPoint.TRAINEE_RESET_PASSWORD)
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .content(objectMapper.writeValueAsString(credentialsDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(traineeService, times(1)).updatePassword(any(CredentialsDTO.class));
    }

    @Test
    public void Should_ReturnAListOfTrainees() throws Exception {
        List<Trainee> trainees = new ArrayList<>();
        trainees.add(TRAINEE1);

        when(traineeService.getAll()).thenReturn(trainees);

        mockMvc.perform(get(EndPoint.TRAINEE + EndPoint.TRAINEE_GET_ALL)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(TRAINEE1.getId().toString())));
    }

    @Test
    public void Should_ReturnTrainee_When_UsernameExist() throws Exception {

        when(traineeService.selectByUsername(USERNAME)).thenReturn(Optional.of(TRAINEE1));

        mockMvc.perform(get(EndPoint.TRAINEE + EndPoint.TRAINEE_USERNAME, USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(TRAINEE1)));
    }

    @Test
    public void Should_ReturnAListOfTrainers_When_UsernameExist() throws Exception {
        List<String> testTrainerList = Arrays.asList("trainer1", "trainer2");
        List<Trainer> testTrainerObjList = new ArrayList<>();

        when(traineeService.updateTrainersList(USERNAME, testTrainerList)).thenReturn(testTrainerObjList);

        mockMvc.perform(put(EndPoint.TRAINEE + EndPoint.TRAINEE_USERNAME, USERNAME)
                        .content(objectMapper.writeValueAsString(testTrainerList))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(testTrainerObjList)));
    }

    @Test
    public void Should_ToggleActiveStatus_When_UserExist() throws Exception {

        when(traineeService.toggleActiveStatus(any(User.class))).thenReturn(TRAINEE1);

        mockMvc.perform(patch(EndPoint.TRAINEE)
                        .content(objectMapper.writeValueAsString(USER))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));

        verify(traineeService, times(1)).toggleActiveStatus(any(User.class));
    }

    @Test
    public void Should_UpdateTraineeProfile_When_TraineeExist() throws Exception {

        when(traineeService.update(any(Trainee.class))).thenReturn(TRAINEE1);

        mockMvc.perform(put(EndPoint.TRAINEE)
                        .content(objectMapper.writeValueAsString(TRAINEE1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(TRAINEE1)));

        verify(traineeService, times(1)).update(any(Trainee.class));
    }

    @Test
    public void Should_DeleteTrainee_When_UsernameExist() throws Exception {

        doNothing().when(traineeService).deleteByUsername(USERNAME);

        mockMvc.perform(delete(EndPoint.TRAINEE + EndPoint.TRAINEE_USERNAME, USERNAME)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));
    }
}
