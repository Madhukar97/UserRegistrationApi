package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.util.Response;

public interface IService {

	public String registerUser(UserDto userdto);
	
	public String updateUser(UserDto userDto, int id);
	
	public String deleteUser(int id);

	public String validateUserLogin(LoginDto loginDto);

	public String resetPassword(String password, String token);
	
	public String verifyuser(String token);

	public String forgotPassword(String email);
	
	public Response saveNote(Note note, String token);

	public String deleteNote(Note note, int id);
}
