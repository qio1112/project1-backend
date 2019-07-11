package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.helper.JWT;
import com.service.UserService;

import io.jsonwebtoken.Claims;

/**
 * JWT token
 * @author Nicholaus
 *
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	// test login
	@PostMapping(value="/auth/login")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> login(@RequestBody UserEntity user, HttpServletResponse response) {
		System.out.println(user);
			// does user exist
			String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword()) ;
			response.addHeader("token", jwtoken);
			if(jwtoken != null) {
				
				Claims claims = JWT.decodeJWT(jwtoken);
				Map<String, Object> tokenMap = new HashMap<>(claims);
				System.out.println(tokenMap.get("iss"));
				return   new HashMap<String, String>() {{
				put("token",jwtoken);
				put("name", (String)tokenMap.get("jti")); // profile name on the token
				put("memberDate", (String) tokenMap.get("sub")); // member date in string formate
				}};
			}
			return null;
	  
	}
		
	// create new user
	@PostMapping(value="/auth/signup")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> signup(@RequestBody UserEntity newUser) {
		return new HashMap<String, String>() {{put("message",userService.signup(newUser));}};
	}
}




/*
 * 
 * 		HttpHeaders responseHeaders = new HttpHeaders();
		String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword() ) ;
//		System.out.print("jwt : "+ jwtoken);
//		response.addHeader("token", jwtoken);
	    responseHeaders.set("token", jwtoken);
		System.out.println("added token to the browser head for user");
		if(jwtoken != null) {
//			System.out.println(user);
			UserEntity theUser = userService.getUserInfo(user.getUsername());
			theUser.setPassword("");
			return ResponseEntity.ok()
				      .headers(responseHeaders)
				      .body(theUser);//theUser;
 * 
 * */


/*
 * 
 * 			
		String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword() ) ;
//		System.out.print("jwt : "+ jwtoken);
		response.addHeader("token", jwtoken);
		System.out.println("added token to the browser head for user");
		if(jwtoken != null) {
//			System.out.println(user);
			UserEntity theUser = userService.getUserInfo(user.getUsername());
			theUser.setPassword("");
			return theUser;
		}
		else 
			return null;
 * */
