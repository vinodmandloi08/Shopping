package com.uhg.userservice.service;

import org.springframework.stereotype.Service;

import com.uhg.userservice.entity.User;
import com.uhg.userservice.utility.AuthenticationToken;

@Service
public interface UserService {

	// Register
	public AuthenticationToken register(User user);

	// Login
	public AuthenticationToken login(User user);

}
