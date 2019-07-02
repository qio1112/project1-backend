package com.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.service.UserService;

/**
 * JWT token
 * @author Nicholaus
 *
 */
@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	// test login
	@PostMapping(value="/auth/login")
	public ResponseEntity<String> login(@RequestBody UserEntity user, HttpServletResponse response) {
			
		String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword() ) ;
		System.out.print("jwt : "+ jwtoken);
		response.addHeader("token", jwtoken);
		System.out.println("added token to the browser head for user");
		if(jwtoken != null) 
			return new ResponseEntity<>("Login success", HttpStatus.OK);
		else 
			return new ResponseEntity<>("Wrong username or password", HttpStatus.FORBIDDEN);
	}
		
	// create new user
	@PostMapping(value="/auth/signup")
	@ResponseStatus(HttpStatus.OK)
	public UserEntity signup(@RequestBody UserEntity newUser) {
		
		userService.signup(newUser);
		return newUser;
	}
}
