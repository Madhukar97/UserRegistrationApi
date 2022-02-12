package com.bridgelabz.fundoo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.customexceptions.InvalidUserException;
import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.Login;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.NoteRepo;
import com.bridgelabz.fundoo.repository.UserRepo;
import com.bridgelabz.fundoo.util.JwtToken;
import com.bridgelabz.fundoo.util.Response;

@Service
public class UserService implements IService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private NoteRepo noteRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private Response response;

	@Override
	public String registerUser(UserDto userDto) {
		System.out.println(userDto.toString());
		// check whether the given email id is present in the database
		User duplicateUser = userRepo.findByEmail(userDto.getEmail());
		
		if (duplicateUser != null) {
			return "Invalid User!...email already used";
		} else {
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
			// map the all entities with userDto
			User user = modelMapper.map(userDto, User.class);
			System.out.println(user.toString());
			// save the user in database
			userRepo.save(user);
			String token = jwtToken.createToken(user.getEmail(), user.getId());
			return token;
		}
	}

	
	@Override
	public String verifyuser(String token) {
		String email = jwtToken.decodeTokenUsername(token);
		User validUser = userRepo.findByEmail(email);
		validUser.setIsVerified(true);
		userRepo.save(validUser);
		return "Email Verification Successful...Now you can login to your account.";
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
		if (validUser.getEmail().equals(login.getEmail()) && passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword()) && validUser.getIsVerified()) {
			String token = jwtToken.createToken(loginDto.getEmail(), validUser.getId());
			return (token);
		}else if (validUser != null) {
			if(!validUser.getPassword().equals(login.getPassword())) return "Invalid Password";
		}
		return "Invalid Email id";
	}

	@Override
	public String resetPassword(String password, String token) {
		String email = jwtToken.decodeTokenUsername(token);
		User validUser = userRepo.findByEmail(email);
		if (validUser != null) {
			validUser.setPassword(passwordEncoder.encode(password));
			validUser.setRegisterDate(LocalDateTime.now());
			userRepo.save(validUser);
			return "Password reset successful....!";
		}
		else return "Invalid credentials....User not found!";
	}

	@Override
	public String forgotPassword(String email) {
		
		return null;
	}


	@Override
	public Response saveNote(Note note, String token) {
		int id = jwtToken.decodeTokenUserId(token);
		User user = userRepo.getById(id);
		note.bindUserToNotes(user);
		noteRepo.save(note);
		response.setStatusCode(200);
		response.setStatusMessage("Note is saved successfully..!");
		response.setToken(token);
		return response;
	}


	@Override
	public String deleteNote(Note note, int id) {
		Note validNote = noteRepo.findById(id).get();
		noteRepo.delete(validNote);
		return "Note is deleted...!";
	}


}





