package com.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.UserEntity;
import com.service.FormulaService;
import com.service.ProjectService;
import com.service.UserService;

@RestController
public class AppController {
	
	@Autowired 
	private ProjectService projectService;
	
	@Autowired 
	private FormulaService formulaService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/login")
	public String login(HttpEntity<String> httpEntity) {
		System.out.println(httpEntity.getBody());
		return "loginlogin";
	}
	
	@GetMapping(value="/test")
	public UserEntity test() {
		return new UserEntity("myusername", "mypassword", "yipeng", new Date());
	}
}
