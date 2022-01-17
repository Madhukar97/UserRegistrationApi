package com.bridgelabz.UserRregistrationApi.service;

import com.bridgelabz.UserRregistrationApi.dto.LoginDto;

public interface ILoginService {

	String validateUserLogin(LoginDto loginDto );

	String validateUserLogin1(LoginDto loginDto);
	
	
}
