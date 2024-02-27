package com.jeovan.gymcrmsystem;

import com.jeovan.gymcrmsystem.facade.FacadeApp;
import com.jeovan.gymcrmsystem.models.Trainee;
import com.jeovan.gymcrmsystem.models.User;
import com.jeovan.gymcrmsystem.services.TraineeServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
class GymCrmSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GymCrmSystemApplication.class, args);
		//FacadeApp facadeApp = (FacadeApp) context.getBean("facadeApp");
		//facadeApp.run();
	}

}
