package com.bridgelabz.UserRregistrationApi.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String phone;
	
	@Column
	private String dob;
	
	@Column
	private String occupation;
	
	@Column
	private LocalDateTime registerDate = LocalDateTime.now();
}
