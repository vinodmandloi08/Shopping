package com.uhg.userservice.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.uhg.userservice.entity.User;


@Component
public class UserDtoConversion {
	
	public User convertUserDtoToUser(UserDto dto) {
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
	public UserDto convertUserToUserDto(User user) {
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

}
