package com.jeovan.gymcrmsystem;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@CucumberContextConfiguration
class GymCrmSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
