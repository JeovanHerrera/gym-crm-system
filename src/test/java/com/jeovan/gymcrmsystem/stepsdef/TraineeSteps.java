package com.jeovan.gymcrmsystem.stepsdef;

import com.jeovan.gymcrmsystem.dtos.responses.AuthTokenDTO;
import com.jeovan.gymcrmsystem.dtos.responses.CredentialsDTO;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


public class TraineeSteps {

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

    @LocalServerPort
    private int port;
    RestTemplate template = new RestTemplate();
    ResponseEntity<CredentialsDTO> credentialsToLogin;
    String token;
    ResponseEntity<Trainee> returnedTrainee;

    @Given("a user wants to register as a trainee")
    public void a_user_wants_to_register_as_a_trainee() {
        USER_FOR_CREATION.setFirstName(FIRST_NAME);
        USER_FOR_CREATION.setLastName(LAST_NAME);
        USER_FOR_CREATION.setIsActive(true);
        TRAINEE_FOR_CREATION.setAddress("Street 1st #2-34");
        TRAINEE_FOR_CREATION.setUser(USER_FOR_CREATION);
    }
    @When("the user provides the required details for the trainee")
    public void the_user_provides_the_required_details_for_the_trainee() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Trainee> request = new HttpEntity<>(TRAINEE_FOR_CREATION, headers);

        String url = "http://localhost:" + port + "/trainee";
        ResponseEntity<CredentialsDTO> responseUser = template.postForEntity(url, request, CredentialsDTO.class);
    }
    @Then("the new trainee should be created")
    public void the_new_trainee_should_be_created() {

    }

    @Given("trainee with username {string} is a registered user")
    public void trainee_with_username_is_a_registered_user(String username) {
        USER_FOR_CREATION.setFirstName(FIRST_NAME);
        USER_FOR_CREATION.setLastName(LAST_NAME);
        USER_FOR_CREATION.setIsActive(true);
        TRAINEE_FOR_CREATION.setAddress("Street 1st #2-34");
        TRAINEE_FOR_CREATION.setUser(USER_FOR_CREATION);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Trainee> request = new HttpEntity<>(TRAINEE_FOR_CREATION, headers);

        String url = "http://localhost:" + port + "/trainee";
        credentialsToLogin = template.postForEntity(url, request, CredentialsDTO.class);

    }

    @And("trainee is authenticated")
    public void trainee_is_authenticated(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CredentialsDTO> request = new HttpEntity<>(credentialsToLogin.getBody(), headers);

        String url = "http://localhost:" + port + "/login";
        ResponseEntity<AuthTokenDTO> tokenResponse = template.postForEntity(url, request, AuthTokenDTO.class);
        token = tokenResponse.getBody().data.get(0);
    }

    @When("requesting details from username {string}")
    public void requesting_details_from_username(String string) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<String>("parameters", headers);
        String url = "http://localhost:" + port + "/trainee/" + string;
        returnedTrainee = template.exchange(url, HttpMethod.GET, request, Trainee.class);
    }

    @Then("existing trainee with username {string} should be returned")
    public void existing_trainee_with_username_should_be_returned(String username) {
        assertEquals(username, returnedTrainee.getBody().getUser().getUsername());
    }

    @And("trainee provides firstName {string} and lastName {string}")
    public void trainee_provides_first_name_and_last_name(String firstName, String lastName) {
        Trainee trainee = returnedTrainee.getBody();
        User user = trainee.getUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        trainee.setUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<Trainee> request = new HttpEntity<>(trainee, headers);

        String url = "http://localhost:" + port + "/trainee";
        returnedTrainee = template.exchange(url, HttpMethod.PUT, request, Trainee.class);
    }
    @Then("trainee with new firstName {string} and lastName {string} should be returned")
    public void trainee_with_new_details_should_be_returned(String firstName, String lastName) {
        assertEquals(firstName, returnedTrainee.getBody().getUser().getFirstName());
        assertEquals(lastName, returnedTrainee.getBody().getUser().getLastName());
    }

}
