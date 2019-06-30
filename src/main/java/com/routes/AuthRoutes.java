package com.routes;

import javax.servlet.http.HttpServlet;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDaoImpl;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author Nicholas Marsden
 *
 * registerRoute-user would be use to create and log a user in with a token 
 * signinRoute - user would be apple sign is and only if exist
 * ----registerRoute and signinRoute both generate a new token signing a user in--
 * logout - removes the token
 * 
 * dependencies (for the json to class instance)
 *jackson-dataformat-xml
 *jackson-mapper-asl
 *jackson-databind
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
//@Consumes(MediaType.APPLICATION_JSON)
public class AuthRoutes {
	ObjectMapper mapper = new ObjectMapper();
	@ResponseBody 
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testAuth() {
		return "auth test successfull";

	}
	/* 
	 * takes the user information does a validation,
	 * store it in the db table users 
	*/
	@RequestMapping(value="/register", method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerRoute(@RequestBody User user) { // signup
		String username = user.getUserName();
		String password = user.getPassword();
		(new UserDaoImpl()).setUserByUsername(username, password);
		System.out.println("register user");
	}
	
	@RequestMapping(value="/signin",  method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void signinRoute(@RequestBody User user) {
		String username = user.getUserName();
		(new UserDaoImpl()).getUserByUsername(username);
		System.out.println("signin user");
	}
	// remove the token so no one else can use it.
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public void logout(String token) {
		System.out.println("logout user");
	}
}
