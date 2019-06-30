package com.dao;

import java.util.List;

import com.entity.ProjectEntity;

public interface ProjectDao {
	
	//select
	List<ProjectEntity> getProjects();
	
	ProjectEntity getProjectById(int id);
	
	ProjectEntity getProjectByName(String name);
	
	//insert
	void insertProject(String name);
}
