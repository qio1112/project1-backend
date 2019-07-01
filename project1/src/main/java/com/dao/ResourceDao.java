package com.dao;

import java.util.List;

import com.entity.ProjectEntity;
import com.entity.ResourceEntity;

public interface ResourceDao {
	
	// select
	List<ResourceEntity> getResourcesByProject(ProjectEntity project);
	
	ResourceEntity getResourceByProjectResourceCode(ProjectEntity project, String resourceCode);
	
	// insert
	void insertResource(ProjectEntity project, String resourceCode, String resourceName);
	
	void insertResource(ResourceEntity newResource);
	
	// update
	// not supposed to be edited
	
	// delete
	void deleteResource(ProjectEntity project, String resourceCode);
}
