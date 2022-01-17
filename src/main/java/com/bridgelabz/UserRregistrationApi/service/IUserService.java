package com.bridgelabz.UserRregistrationApi.service;

import com.bridgelabz.UserRregistrationApi.dto.UserDto;

public interface IUserService {

	String updateUser(UserDto userDto, int id);
	
	String saveUser(UserDto userDto);
	
	String deleteUser(int id);
	
	
}
