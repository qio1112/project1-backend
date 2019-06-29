package com.routes;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;


/**
 * 
 * @author Nicholas Marsden
 *
 *
 *
 */

/*
 * @ResponseBody 
 * - use http messages converters to convert the return value to http response body
 * 
 * 
 * @ModelAttribute("SpringWeb")User user
 * the above method was said to be able to use on forms 
 * dont think it would be compatible with anglare framework,
 * its a good fit for the jsp though
*/
@RestController
@RequestMapping(value="/auth")
public class AuthRoutes {
	@ResponseBody 
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testAuth() {
		return "auth test successfull";

	}
/* 
 * takes the user information does a validation,
 * store it in the db table users 
*/
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public void registerRoute(@RequestBody User user) { // signup
		System.out.println("register user");
	}
	
	@RequestMapping(value="/signin",  method= RequestMethod.POST)
	public void signinRoute(@RequestBody User user) {
		System.out.println("signin user");
	}
	// remove the token so no one else can use it.
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public void logout(String token) {
		System.out.println("logout user");
	}
}

