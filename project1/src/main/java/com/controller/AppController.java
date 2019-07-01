package com.controller;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.entity.ProjectEntity;
import com.entity.UserEntity;
import com.model.ProjectData;
import com.service.DataService;
import com.service.FormulaPageDataService;
import com.service.UserService;

@RestController
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private DataService dataService;
	
	@Autowired
	private FormulaPageDataService formulaPageDataService;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	@PostMapping(value="/login")
	public String login(HttpEntity<String> httpEntity) {
		System.out.println(httpEntity.getBody());
		return "loginlogin";
	}
	
	@PostMapping(value="/signup")
	@ResponseStatus(HttpStatus.OK)
	public UserEntity signup(@RequestBody UserEntity newUser) {
		
		userService.signup(newUser);
		return newUser;
	}
	
	@GetMapping(value="/projects")
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectEntity> getProjects() {
		
		return dataService.getProjects();
	}
	
	@GetMapping(value="/project/{projectName}?page={page}&page_rows={numPerPage}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectData getProjectDataByProjectNameAngPage(@PathVariable String projectName, @PathVariable int page, @PathVariable int numPerPage) {
		
		return dataService.getProjectDataByNameAngPage(projectName, page, numPerPage);
	}
	
//	@PostMapping(value="/project/")
	
	@GetMapping(value="/test")
	@ResponseStatus(HttpStatus.OK)
	public UserEntity test() {
		System.out.println(this.sessionFactory == null);
		return new UserEntity("myusername", "mypassword", "yipeng", new Date());
	}
}
