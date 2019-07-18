package com.dao;

import java.util.List;

import com.entity.ProjectEntity;
import com.exception.DataNotFoundException;

/**
 * DAO of project information
 * @author yipeng
 *
 */
public interface ProjectDao {
	
	//select
	List<ProjectEntity> getProjects() throws DataNotFoundException;
	
	ProjectEntity getProjectById(int id) throws DataNotFoundException;
	
	ProjectEntity getProjectByName(String name) throws DataNotFoundException;
	
	//insert
	void insertProject(String name);
	
	void insertProject(ProjectEntity newProject);
}
