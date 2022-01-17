package com.bridgelabz.UserRregistrationApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.UserRregistrationApi.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
}
