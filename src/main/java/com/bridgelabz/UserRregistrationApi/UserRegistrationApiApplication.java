package com.bridgelabz.UserRregistrationApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class })
public class UserRegistrationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApiApplication.class, args);
	}

}
