package com.uhg.userservice.utility;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationToken {

	private String token;
	private String email;
	private String role;
}
