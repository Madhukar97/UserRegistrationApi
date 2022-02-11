package com.bridgelabz.fundoo.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepo;
import com.bridgelabz.fundoo.util.JwtToken;

public class EmailService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtToken jwtToken;
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendVerificationEmail(UserDto userDto, String siteUrl) throws UnsupportedEncodingException, MessagingException{
		User validUser = userRepo.findByEmail(userDto.getEmail());
		String token = jwtToken.createToken(validUser.getEmail(), validUser.getId());
		String verifyUrl = siteUrl + "verify?token=" + token;
		String subject = "Please verify your registration";
		String senderName = "Fundoo Team";
		String mailContent = "<p>Please click link below to verify your registration email</p>";
		mailContent += "<a href = " + verifyUrl +  ">VERIFY</a>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
	
		helper.setFrom(System.getenv("fundooemail"), senderName);
		helper.setTo(userDto.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		
		mailSender.send(message);
	}
	
	public String sendForgotPassEmail(String email, String siteUrl) throws UnsupportedEncodingException, MessagingException{
		User validUser = userRepo.findByEmail(email);
		String token = jwtToken.createToken(validUser.getEmail(), validUser.getId());
		String verifyUrl = siteUrl + "resetpassword?token=" + token;
		String subject = "Please click on the link to reset your password";
		String senderName = "Fundoo Team";
		String mailContent = "<p>Please click link below to reset your password</p>";
		mailContent += "<a href = " + verifyUrl +  ">Reset Password</a>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
	
		helper.setFrom("${spring.mail.username}", senderName);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		
		mailSender.send(message);
		return "Check your mail to reset your password";
	}
}
