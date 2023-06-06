package com.uhg.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uhg.userservice.dto.UserDto;
import com.uhg.userservice.dto.UserDtoConversion;
import com.uhg.userservice.error.ErrorMessage;
import com.uhg.userservice.service.UserService;
import com.uhg.userservice.utility.AuthenticationToken;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserDtoConversion conversion;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto dto, BindingResult result){
		
		logger.info("Register Method calls from User Controller");
		
		if(result.hasErrors()) {
			
			logger.info("Request Body has errors - User Controller - Register Method");
			
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		AuthenticationToken token = service.register(conversion.convertUserDtoToUser(dto));
		
		if(token == null) {
			
			logger.info("User already exists - User Controller - Register Method");
			
			return ResponseEntity.badRequest().body(new ErrorMessage("User with this email already exists"));
		}
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto dto){
		AuthenticationToken token = service.login(conversion.convertUserDtoToUser(dto));
		if(token == null) {
			return ResponseEntity.badRequest().body(new ErrorMessage("Please enter correct credentials"));
		}
		return ResponseEntity.ok(token);
	}
}