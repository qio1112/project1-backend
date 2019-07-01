package com.service;

import java.util.List;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.model.ProjectData;

public interface DataService {
	
	List<ProjectEntity> getProjects();

	ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage);
	
	void insertDataToProject(ProjectData projectData);
	
	void updataData(DataEntity newDataEntity);
	
}
