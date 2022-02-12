package com.bridgelabz.fundoo.model;

import java.time.LocalDateTime;
import java.util.List;

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
	private Boolean isVerified = false;
	
	@Column
	private LocalDateTime registerDate = LocalDateTime.now();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Note> notes;
}
