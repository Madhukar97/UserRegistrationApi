package com.bridgelabz.UserRregistrationApi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.bridgelabz.UserRregistrationApi.dto.LoginDto;
import com.bridgelabz.UserRregistrationApi.entity.Login;
import com.bridgelabz.UserRregistrationApi.entity.User;
import com.bridgelabz.UserRregistrationApi.repository.UserRepo;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper1;

	@Bean
	public ModelMapper modelMapper1() {
		return new ModelMapper();
	}
	
	@Override
	public String validateUserLogin(LoginDto loginDto) {
		Login login = modelMapper1.map(loginDto, Login.class);
		List<User> userList = userRepo.findAll();
		for (User currentuser : userList) {
			if (currentuser.getEmail().equals(login.getEmail()) && currentuser.getPassword().equals(login.getPassword())) {
				return ("Login successful....Welcome "+ currentuser.getFirstName());
			}
		}
		return "Invalid Email_id or Password";
	}
	
	@Override
	public String validateUserLogin1(LoginDto loginDto) {
		Login login = modelMapper1.map(loginDto, Login.class);
		User validUser = userRepo.findByEmail(loginDto.getEmail());
		if (validUser.getEmail().equals(login.getEmail()) && validUser.getPassword().equals(login.getPassword())) {
			return ("Login successful....Welcome "+ validUser.getFirstName());
		}else if (validUser != null) {
			if(!validUser.getPassword().equals(login.getPassword())) return "Invalid Password";
		}
		else if(validUser == null) return "Invalid Email_id or Password";
		return null;
	}
}
