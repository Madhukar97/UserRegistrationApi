package com.bridgelabz.UserRregistrationApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.UserRregistrationApi.dto.LoginDto;
import com.bridgelabz.UserRregistrationApi.dto.UserDto;
import com.bridgelabz.UserRregistrationApi.model.User;
import com.bridgelabz.UserRregistrationApi.repository.UserRepo;
import com.bridgelabz.UserRregistrationApi.service.IService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private IService iService;

	@GetMapping("/")
	public ResponseEntity<String> getWelcomeMsg(){
		return new ResponseEntity<String>("Welcome to user registration form",HttpStatus.OK);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/register_user")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
		String msg = iService.registerUser(userDto); 
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserDto user) {
		String msg = iService.updateUser(user, id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id){
		String msg = iService.deleteUser(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK) ;
	}
	
	@GetMapping("/user_login")
	public ResponseEntity<String> validateUserLogin(@RequestBody LoginDto loginDto){
		String msg = iService.validateUserLogin(loginDto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}	
	
	
}
