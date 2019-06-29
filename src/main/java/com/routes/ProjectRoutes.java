package com.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	public void getProjectName() {
		System.out.println("get project names");

	}
	
	// updates one cell in the table 
	@RequestMapping(value="/edit_cell",method= RequestMethod.PATCH)
	public void updateProjectCell() {
		System.out.println("update project cell");

	}
	
	@RequestMapping(value="/add_row",method= RequestMethod.PUT)
	public void addRowToProject() {
		System.out.println("add row");

	}
	@RequestMapping(value="/add_column", method= RequestMethod.PUT)
	public void addColumnToProject() {
		System.out.println("add column");
	}
	//get all data of a project(resource) by project name
	//?page=10&page_rows=20
	@RequestMapping(value="/{project_name}", method= RequestMethod.GET)
	public void getProjectSubRow() {
		System.out.println("get project sub row");

	}
	@RequestMapping(value="/delete_column/{project_id}/{column_name}",method= RequestMethod.DELETE)
	public void deleteColumnToProject() {
		System.out.println("delete column");
	}
}
