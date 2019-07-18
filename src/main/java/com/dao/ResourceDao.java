package com.dao;

import java.util.List;

import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.exception.DataNotFoundException;

/**
 * DAO of resource information of projects
 * @author yipeng
 *
 */
public interface ResourceDao {
	
	// select
	List<ResourceEntity> getResourcesByProject(ProjectEntity project) throws DataNotFoundException;
	
	ResourceEntity getResourcesByProjectResourceCode(ProjectEntity project, String resourceCode) throws DataNotFoundException;
	
	ResourceEntity getResourceByProjectResourceCode(ProjectEntity project, String resourceCode) throws DataNotFoundException;
	
	// insert
	void insertResource(ProjectEntity project, String resourceCode, String resourceName);
	
	void insertResource(ResourceEntity newResource);
	
	// update
	// not supposed to be edited
	
	// delete
	void deleteResource(ProjectEntity project, String resourceCode);
}
