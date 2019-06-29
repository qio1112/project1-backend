package com.routes;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.entities.Project;
import model.entities.ProjectDetail;
/**
 * 
 * @author Nicholas Marsden
 *
 *Yipeng wanted a message class to be the response of each request 
 *so i create a message class
 *To send back the String he wanted 
 */

@RestController
@RequestMapping(value="/project")
public class ProjectRoutes {
	
	@ResponseBody
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testProject() {
		return "project test successfull";

	}

	//get all project (resource) names
	@RequestMapping(value="/",method= RequestMethod.GET)
	public ArrayList<Project> getProjectName() {		
		System.out.println("get project names");
		
		// return an array of projects
		ArrayList<Project> projects= new ArrayList<Project>();
		return projects;

	}
	
	/**
	 * 
	 * @param projectColumn -- gives the project_id and record for the user
	 * @param value -- finds out what the user want to change the value to
	 */
	
	// updates one cell in the table 
	@RequestMapping(value="/edit_cell",method= RequestMethod.PATCH)
	public void updateProjectCell(@RequestBody ProjectDetail projectColumn, @RequestParam int value) {
		// we need the project_id , record_code and the column name and value to change to 
		System.out.println("update project cell");

	}
	
	
	@RequestMapping(value="/add_row",method= RequestMethod.PUT)
	public void addRowToProject(@RequestBody Project edit_project) {
		// multiple rows in which the user could have changes so you have to check each columns rows
		System.out.println("add row");

	}
	@RequestMapping(value="/add_column", method= RequestMethod.PUT)
	public void addColumnToProject(@RequestBody ProjectDetail projectColumn) {
		// 
		System.out.println("add column");
	}
	//get all data of a project(resource) by project name
	/**
	 * 
	 * @param project_id -- get the project they are working on
	 * @param page     --   get the number of page
	 * @param page_rows -- gets the number of rows allowed
	 */
	@RequestMapping(value="/{project_id}", method= RequestMethod.GET)
	public void getProjectSubRow(@PathVariable("project_id") int project_id, @RequestParam(name = "page") int page, @RequestParam(name="page_rows") int page_rows) {
		System.out.println("project id :" +project_id + " page: "+ page + " page_rows: " + page_rows);
		System.out.println("get project sub row");

	}
	@RequestMapping(value="/delete_column/{project_id}/{column_name}",method= RequestMethod.DELETE)
	public void deleteColumnToProject(@PathVariable("project_id") int project_id, @PathVariable("column_name") String column_name) {
		System.out.println( project_id + " " + column_name);
		System.out.println("delete column");
	}
}
