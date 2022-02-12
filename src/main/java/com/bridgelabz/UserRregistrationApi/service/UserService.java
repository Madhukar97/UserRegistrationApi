package com.bridgelabz.UserRregistrationApi.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.UserRregistrationApi.customExceptions.InvalidUserException;
import com.bridgelabz.UserRregistrationApi.dto.LoginDto;
import com.bridgelabz.UserRregistrationApi.dto.UserDto;
import com.bridgelabz.UserRregistrationApi.model.Login;
import com.bridgelabz.UserRregistrationApi.model.User;
import com.bridgelabz.UserRregistrationApi.repository.UserRepo;
import com.bridgelabz.UserRregistrationApi.util.JwtToken;

@Service
public class UserService implements IService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtToken jwtToken;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public String registerUser(UserDto userDto) {
		// check whether the given email id is present in the database
		User duplicateUser = userRepo.findByEmail(userDto.getEmail());
		if (duplicateUser != null) {
			return "Invalid User!...email already used";
		} else {
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
			// map the all entities with userDto
			User user = modelMapper.map(userDto, User.class);
			// save the user in database
			userRepo.save(user);
			return "User saved...!";
		}
	}

	@Override
	public String updateUser(UserDto userDto, int id) {
		User updatedUser = null;
		User user = modelMapper.map(userDto, User.class);
		try {
			updatedUser = userRepo.findById(id).get();
		} catch (Exception e) {

		}
		if (updatedUser == null) {
			return "User not found..!";
		}
		updatedUser.setFirstName(user.getFirstName());
		updatedUser.setLastName(user.getLastName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setPhone(user.getPhone());
		updatedUser.setDob(user.getDob());
		updatedUser.setOccupation(user.getOccupation());
		userRepo.save(updatedUser);
		return "User Updated...";
	}

	@Override
	public String deleteUser(int id) {
		Optional<User> deleteUser = userRepo.findById(id);
		try {
			if (!deleteUser.isPresent()) {
				throw new InvalidUserException("Invalid Endpoint Url...User not found...!!");
			}else {
				userRepo.delete(deleteUser.get());
				return ("User deleted with id: " + id);
			}
		}catch (InvalidUserException e) {
			return "Error: " + e.getMessage();
		}
	}

	@Override
	public String validateUserLogin(LoginDto loginDto) {
		Login login = modelMapper.map(loginDto, Login.class);
		User validUser = userRepo.findByEmail(loginDto.getEmail());
		if (validUser.getEmail().equals(login.getEmail()) && passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword())) {
			String token = jwtToken.createToken(loginDto.getEmail(), validUser.getId());
			return ("Login successful....Welcome "+ validUser.getFirstName() + "\n JWT : "+ token);
		}else if (validUser != null) {
			if(!validUser.getPassword().equals(login.getPassword())) return "Invalid Password";
		}
		return "Invalid Email id";
	}

	@Override
	public String resetPassword(String password, String token) {
		String email = jwtToken.decodeToken(token);
		User validUser = userRepo.findByEmail(email);
		if (validUser != null) {
			validUser.setPassword(passwordEncoder.encode(password));
			validUser.setRegisterDate(LocalDateTime.now());
			userRepo.save(validUser);
			return "Password reset successful....!";
		}
		else return "Invalid credentials....User not found!";
	}

}





