package com.bridgelabz.UserRregistrationApi.service;

import com.bridgelabz.UserRregistrationApi.dto.LoginDto;
import com.bridgelabz.UserRregistrationApi.dto.UserDto;

public interface IService {

	public String registerUser(UserDto userdto);
	
	public String updateUser(UserDto userDto, int id);
	
	public String deleteUser(int id);

	public String validateUserLogin(LoginDto loginDto);
	
	
}
