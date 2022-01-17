package com.bridgelabz.UserRregistrationApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.UserRregistrationApi.util.JwtToken;

@Configuration
public class AppConfiguration {
	
	//Password Encoder configuration bean
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	//JwtToken configuration bean
	@Bean
	public JwtToken jwtToken() {
		return new JwtToken();
	}
}
