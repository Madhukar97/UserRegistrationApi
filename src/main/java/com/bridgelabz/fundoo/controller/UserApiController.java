package com.bridgelabz.fundoo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.Note;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepo;
import com.bridgelabz.fundoo.service.EmailService;
import com.bridgelabz.fundoo.service.IService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserApiController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private IService iService;
	
	@Autowired
	private EmailService emailService;

	@GetMapping("/")
	public ResponseEntity<String> getWelcomeMsg(){
		return new ResponseEntity<String>("Welcome to user registration form",HttpStatus.OK);
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) throws UnsupportedEncodingException, MessagingException {
		String msg = iService.registerUser(userDto);
		String siteUrl = "http://localhost:8080/";
		emailService.sendVerificationEmail(userDto, siteUrl);
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
	
	@PostMapping("/user_login")
	public ResponseEntity<String> validateUserLogin(@RequestBody LoginDto loginDto){
		String msg = iService.validateUserLogin(loginDto);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}	
	
	@PutMapping("/resetpassword")
	public ResponseEntity<String> resetPassword (@RequestParam String password, @RequestHeader String token) {
		String msg = iService.resetPassword(password, token);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@RequestParam String token) {
		String msg = iService.verifyuser(token);
		return msg;
	}

	
	@PostMapping("/forgotPass")
	public ResponseEntity<String> updateUser(@PathVariable String email) throws UnsupportedEncodingException, MessagingException {
		String siteUrl = "http://localhost:8080/";
		String msg = emailService.sendForgotPassEmail(email, siteUrl);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	
}
