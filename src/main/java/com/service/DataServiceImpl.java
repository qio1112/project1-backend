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
import com.exception.DataNotFoundException;
import com.model.ProjectData;

/**
 * service of project(resource) page data
 * @author yipeng
 *
 */
@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	@Transactional
	public List<ProjectEntity> getProjects() throws DataNotFoundException {
		return projectDao.getProjects();
	}
	
	@Override
	@Transactional
	public ProjectEntity getProjectByName(String name) throws DataNotFoundException {
		
		return projectDao.getProjectByName(name);
	}

	@Override
	@Transactional
	public ProjectData getProjectDataByNameAngPage(String projectName, int page, int numPerPage) throws DataNotFoundException {
		
		if(page <= 0 || numPerPage <= 0) {
			throw new DataNotFoundException();
		}
		
		ProjectEntity project = projectDao.getProjectByName(projectName);
		
		List<DataEntity> data = dataDao.getDataByProjectAndRange(project, (page - 1) * numPerPage + 1, page * numPerPage);
				
		List<ResourceEntity> resource = new ArrayList<>();
		
		for(DataEntity dataEntity : data) {
			String curResourceCode = dataEntity.getResourceCode();
			resource.add(resourceDao.getResourceByProjectResourceCode(project, curResourceCode));
		}
		
		return new ProjectData(project, data.toArray(new DataEntity[data.size()]), resource.toArray(new ResourceEntity[resource.size()]));
	}
	
	@Override
	@Transactional
	public ProjectEntity createProject(String projectName) {
		
		projectDao.insertProject(projectName);
		try {
			return projectDao.getProjectByName(projectName);
		} catch (DataNotFoundException e) {
			return null;
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional
	public void updataData(DataEntity newDataEntity) {
		
		dataDao.updateData(newDataEntity);
	}

	@Override
	@Transactional
	public List<ResourceEntity> getResourceByProject(ProjectEntity project) throws DataNotFoundException {
		
		return resourceDao.getResourcesByProject(project);
	}

	@Override
	@Transactional
	public void insertDataToData(DataEntity data) {

		dataDao.insertData(data);
	}

	@Override
	@Transactional
	public int deleteColumnOfProject(ProjectEntity project, String columnName) {
		
		return dataDao.deleteDataByProjectColumnName(project, columnName);
	}

	
	
}
