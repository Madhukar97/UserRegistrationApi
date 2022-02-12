package com.bridgelabz.fundoo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgelabz.fundoo.service.EmailService;
import com.bridgelabz.fundoo.util.JwtToken;
import com.bridgelabz.fundoo.util.Response;

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
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public EmailService emailService() {
		return new EmailService();
	}
	
	@Bean
	public Response response() {
		return new Response();
	}
}
