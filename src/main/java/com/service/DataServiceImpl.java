package com.service;

import java.util.ArrayList;
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

/**
 * service of project(resource) page data
 * @author yipeng
 *
 */
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
	public ProjectEntity getProjectByName(String name) {
		
		return projectDao.getProjectByName(name);
	}

	@Override
	public ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage) {
		
		ProjectEntity project = projectDao.getProjectByName(projectName);
		
		List<DataEntity> data = dataDao.getDataByProjectAndRange(project, page * numPerPage, page * numPerPage + numPerPage - 1);
		
		System.out.println(data.get(0));
		
		List<ResourceEntity> resource = new ArrayList<>();
		
		for(DataEntity dataEntity : data) {
			String curResourceCode = dataEntity.getResourceCode();
			resource.add(resourceDao.getResourceByProjectResourceCode(project, curResourceCode));
		}
		
		return new ProjectData(project, data.toArray(new DataEntity[data.size()]), resource.toArray(new ResourceEntity[resource.size()]));
	}
	
	@Override
	public ProjectEntity createProject(String projectName) {
		
		projectDao.insertProject(projectName);
		return projectDao.getProjectByName(projectName);
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

	@Override
	public List<ResourceEntity> getResourceByProject(ProjectEntity project) {
		
		return resourceDao.getResourcesByProject(project);
	}

	@Override
	public void insertDataToData(DataEntity data) {

		dataDao.insertData(data);
	}

	@Override
	public int deleteColumnOfProject(ProjectEntity project, String columnName) {
		
		return dataDao.deleteDataByProjectColumnName(project, columnName);
	}

	
	
}
