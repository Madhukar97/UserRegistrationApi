package com.bridgelabz.UserRregistrationApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.UserRregistrationApi.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	public User findByEmail(String email);
	
}
