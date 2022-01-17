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
import com.bridgelabz.UserRregistrationApi.entity.User;
import com.bridgelabz.UserRregistrationApi.repository.UserRepo;
import com.bridgelabz.UserRregistrationApi.service.ILoginService;
import com.bridgelabz.UserRregistrationApi.service.IUserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private ILoginService iLoginSerivice;

	@GetMapping("/")
	public ResponseEntity<String> getWelcomeMsg(){
		return new ResponseEntity<String>("Welcome to user registration form",HttpStatus.OK);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/save_user")
	public String saveUser(@RequestBody UserDto userDto) {
		String msg = service.saveUser(userDto); 
		return msg;
	}
	
	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable int id, @RequestBody UserDto user) {
		String msg = service.updateUser(user, id);
		return msg;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id){
		String msg = service.deleteUser(id);
		return msg ;
	}
	
	@GetMapping("/user_login")
	public ResponseEntity<String> validateUserLogin(@RequestBody LoginDto loginDto){
		String msg = iLoginSerivice.validateUserLogin1(loginDto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}	
	
}
