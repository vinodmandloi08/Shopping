package com.uhg.userservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uhg.userservice.entity.User;
import com.uhg.userservice.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user-register")
	public ResponseEntity<?> userRegister(@RequestBody User user){
		Optional<User> users = userRepository.findByEmail(user.getEmail());
		if(users.isEmpty()) {
			userRepository.save(user);
			return ResponseEntity.ok(users);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/user-login")
	public ResponseEntity<?> userLogin(@RequestBody User user){
		Optional<User> users = userRepository.findByEmail(user.getEmail());
		if(users.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		
		User newUser = users.get();
		if(user.getEmail().equals(newUser.getEmail()) && user.getPassword().equals(newUser.getPassword())) {
			return ResponseEntity.ok(newUser);
		}
		return ResponseEntity.badRequest().build();
	}

}
