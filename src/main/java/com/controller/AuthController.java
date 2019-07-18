package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.dao.UserDao;
import com.entity.UserEntity;
import com.exception.DataAlreadyExistsException;
import com.exception.DataNotFoundException;
import com.service.UserService;

/**
 * 
 * @author Nicholaus, Yipeng
 *
 */
@CrossOrigin(origins="*")
@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	
	// test login
	@PostMapping(value="/auth/login")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> login(@RequestBody UserEntity user, HttpServletResponse response) {
		
		if(user != null && user.getUsername() != null && user.getPassword() != null) {
			UserEntity theUser = null;
			try {
				theUser = userService.getUserInfo(user.getUsername()); 
				theUser.setPassword("");
			} catch (DataNotFoundException e) { // no such user
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username or password");
			}
			String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword());	
			if (jwtoken == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username or password");
			}
			Map<String, Object> res = new HashMap<>();
			res.put("message", "login success");
			res.put("data", theUser);
			res.put("token", jwtoken);
			res.put("expiresIn", UserDao.TOKEN_EXPIRES);
			// put expiration date s
			return res;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid username or password");
		}
	}
		
	// create new user
	@PostMapping(value="/auth/signup")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> signup(@RequestBody UserEntity newUser) {
		
		try {
			userService.signup(newUser);
			newUser.setPassword("");
			Map<String, Object> res = new HashMap<>();
			res.put("message", "User " + newUser.getUsername() + " created");
			res.put("data", newUser);
			return res;
		} catch (DataAlreadyExistsException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT);
		}
	}
}
