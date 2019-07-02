package com.dao;

import java.util.List;

import com.entity.ProjectEntity;

/**
 * DAO of project information
 * @author yipeng
 *
 */
public interface ProjectDao {
	
	//select
	List<ProjectEntity> getProjects();
	
	ProjectEntity getProjectById(int id);
	
	ProjectEntity getProjectByName(String name);
	
	//insert
	void insertProject(String name);
	
	void insertProject(ProjectEntity newProject);
}
