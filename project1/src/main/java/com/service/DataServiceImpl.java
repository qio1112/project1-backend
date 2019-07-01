package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DataDao;
import com.dao.ProjectDao;
import com.dao.ResourceDao;
import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.model.ProjectData;

@Service
@Transactional
public class DataServiceImpl implements DataService{
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public List<ProjectEntity> getProjects() {
		return projectDao.getProjects();
	}

	@Override
	public ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage) {
		
		ProjectEntity project = projectDao.getProjectByName(projectName);
		
		List<DataEntity> data = dataDao.getDataByProjectAndRange(new ProjectEntity(projectName), page * numPerPage, page * numPerPage + numPerPage - 1);
		
		List<ResourceEntity> resource = resourceDao.getResourcesByProject(project);
		
		return new ProjectData(project, data.toArray(new DataEntity[data.size()]), resource.toArray(new ResourceEntity[resource.size()]));
	}

	@Override
	public void insertDataToProject(ProjectData projectData) {
		
//		ProjectEntity project = projectData.getProject();
		
		DataEntity[] data = projectData.getData();
		
		ResourceEntity[] resource = projectData.getResource();
		
		for(DataEntity dataEntity : data) {
			dataDao.insertData(dataEntity);
		}
		
		for(ResourceEntity resourceEntity : resource) {
			resourceDao.insertResource(resourceEntity);
		}
	}

	@Override
	public void updataData(DataEntity newDataEntity) {
		
		dataDao.updateData(newDataEntity);
	}
	
	
}
