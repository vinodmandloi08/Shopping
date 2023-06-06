package com.uhg.userservice.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.uhg.userservice.entity.User;
import com.uhg.userservice.repository.UserRepository;
import com.uhg.userservice.utility.AuthenticationToken;
import com.uhg.userservice.utility.JwtService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	// Register
	@Override
	public AuthenticationToken register(User user) {
		
		if(repository.findByEmail(user.getEmail()).isPresent()) {
			return null;
		}
		
		logger.info("Captcha Verified Successfully from Register Method of UserServiceImpl");
		
		User savedUser = new User();
		savedUser.setEmail(user.getEmail());
		savedUser.setName(user.getName());
		savedUser.setPassword(encoder.encode(user.getPassword()));
		savedUser.setRole("ROLE_USER");
		
		repository.save(savedUser);
		
		String jwtToken = jwtService.generateToken(user);
		
		return new AuthenticationToken(jwtToken,user.getEmail(),savedUser.getRole());
	}

	// Login
	@Override
	public AuthenticationToken login(User user) {

		logger.info("Captcha Verified Successfully from Login Method of UserServiceImpl");
		
		try {
			manager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (Exception e) {
			return null;
		}
		Optional<User> fetchedUser = repository.findByEmail(user.getEmail());
		if(fetchedUser.isEmpty()) {
			return null;
		}
		String jwtToken = jwtService.generateToken(fetchedUser.get());
		return new AuthenticationToken(jwtToken,user.getEmail(),fetchedUser.get().getRole());
	}
}