package com.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author Nicholas Marsden
 *
 *
 *@ResponseBody - use http messages converters to convert the return value to http response body
 */
@RestController
@RequestMapping(value="/auth")
public class AuthRoutes {
	@ResponseBody 
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testAuth() {
		return "auth test successfull";

	}
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public void registerRoute() { // signup
		System.out.println("register user");
	}
	@RequestMapping(value="/signin",  method= RequestMethod.POST)
	public void signinRoute() {
		System.out.println("signin user");
	}
	@RequestMapping(value="/logout", method= RequestMethod.GET)
	public void logout() {
		System.out.println("logout user");
	}
}
