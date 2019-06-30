package com.dao;

import java.util.List;

import com.entity.DataEntity;
import com.entity.ProjectEntity;

public interface DataDao {
	
	// selection
	
	List<DataEntity> getDataByProject(ProjectEntity project);
	
	List<DataEntity> getDataByProjectResourceCode(ProjectEntity project, String resourceCode);
	
	List<DataEntity> getDataByProjectAndRange(ProjectEntity project, int start, int end);
	
	DataEntity getDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName);
	
	// insertion
	void insertData(ProjectEntity project, String resourceCode, String columnName, String value, String type);
	
	// update
	void updateData(DataEntity updatedData);
	
	// deletion
	int deleteData(ProjectEntity project, String resourceCode, String column_name);
	
	int deleteDataByProjectIdResourceId(ProjectEntity project, String resourceCode);
	
}
