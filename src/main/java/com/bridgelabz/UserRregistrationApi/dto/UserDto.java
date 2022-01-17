package com.bridgelabz.UserRregistrationApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int age;
	private String occupation;

}
