package com.service;

import java.util.List;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.model.ProjectData;

/**
 * service of project(resource) page data
 * @author yipeng
 *
 */
public interface DataService {
	
	List<ProjectEntity> getProjects();
	
	ProjectEntity createProject(String projectName);
	
	ProjectEntity getProjectByName(String projectName);
	
	List<ResourceEntity> getResourceByProject(ProjectEntity project);

	ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage);
	
	void insertDataToProject(ProjectData projectData);
	
	void insertDataToData(DataEntity data);
	
	void updataData(DataEntity newDataEntity);
	
	int deleteColumnOfProject(ProjectEntity project, String columnName);
}
