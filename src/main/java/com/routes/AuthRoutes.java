package com.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthRoutes {
	@RequestMapping("/auth/test")
	public void testAuth() {
		System.out.println("auth test");

	}
	@RequestMapping("/auth/register")
	public void registerRoute() { // signup
		System.out.println("register user");
	}
	@RequestMapping("/auth/signin")
	public void signinRoute() {
		System.out.println("signin user");
	}
	@RequestMapping("/auth/logout")
	public void logout() {
		System.out.println("logout user");
	}
}
