package com.bridgelabz.UserRregistrationApi.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.bridgelabz.UserRregistrationApi.customExceptions.InvalidUserException;
import com.bridgelabz.UserRregistrationApi.dto.UserDto;
import com.bridgelabz.UserRregistrationApi.entity.User;
import com.bridgelabz.UserRregistrationApi.repository.UserRepo;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
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
		updatedUser.setAge(user.getAge());
		updatedUser.setOccupation(user.getOccupation());
		userRepo.save(updatedUser);
		return "User Updated...";
	}

	@Override
	public String saveUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		List<User> userList = userRepo.findAll();
		for (User currentuser : userList) {
			if (currentuser.getEmail().equals(user.getEmail())) {
				return "Invalid User!...email already used";
			}
		}
		userRepo.save(user);
		return "User saved...!";
	}

	@Override
	public String deleteUser(int id) {
		Optional<User> deleteUser = null;
		String msg = null;
		deleteUser = userRepo.findById(id);
		if (deleteUser == null)
			throw new InvalidUserException("Invalid Endpoint Url...User not found...!!");

		if (deleteUser.isPresent()) {
			userRepo.delete(deleteUser.get());
			return ("User deleted with id: " + id);
		}
		return msg;
	}
}
