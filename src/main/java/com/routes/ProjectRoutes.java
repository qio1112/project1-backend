package com.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectRoutes {
	
	
	@RequestMapping("/project/test")
	public void testProject() {
		System.out.println("project user");

	}

	//get all project (resource) names
	@RequestMapping("/project")
	public void getProjectName() {
		System.out.println("get project names");

	}
	//get all data of a project(resource) by project name
	@RequestMapping("project/{project_name}?page=10&page_rows=20")
	public void getProjectSubRow() {
		System.out.println("get project sub row");

	}
	// updates one cell in the table 
	@RequestMapping("/project/edit_cell")
	public void updateProjectCell() {
		System.out.println("update project cell");

	}
	
	@RequestMapping("/project/add_row")
	public void addRowToProject() {
		System.out.println("add row");

	}
	@RequestMapping("/project/add_column")
	public void addColumnToProject() {
		System.out.println("add column");
	}
	@RequestMapping("/project/delete_column/{project_id}/{column_name}")
	public void deleteColumnToProject() {
		System.out.println("delete column");
	}
}
