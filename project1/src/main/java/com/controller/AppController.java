package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.FormulaService;
import com.service.ProjectService;
import com.service.UserService;

@RestController
@RequestMapping("/project1")
public class AppController {
	
	@Autowired 
	private ProjectService projectService;
	
	@Autowired 
	private FormulaService formulaService;
	
	@Autowired
	private UserService userService;
	
	
}
