package com.service;

import java.util.List;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.exception.DataNotFoundException;
import com.model.ProjectData;

/**
 * service of project(resource) page data
 * @author yipeng
 *
 */
public interface DataService {
	
	List<ProjectEntity> getProjects() throws DataNotFoundException;
	
	ProjectEntity createProject(String projectName);
	
	ProjectEntity getProjectByName(String projectName) throws DataNotFoundException;
	
	List<ResourceEntity> getResourceByProject(ProjectEntity project) throws DataNotFoundException;

	ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage) throws DataNotFoundException;
	
	void insertDataToProject(ProjectData projectData);
	
	void insertDataToData(DataEntity data);
	
	void updataData(DataEntity newDataEntity);
	
	int deleteColumnOfProject(ProjectEntity project, String columnName);
}
