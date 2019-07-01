package com.dao;

import java.util.List;

import com.entity.FormulaPageDataEntity;
import com.entity.ProjectEntity;

/**
 * DAO of non-formula data in formula page
 * @author yipeng
 *
 */
public interface FormulaPageDataDao {
	
	// selection
	List<FormulaPageDataEntity> getFormulaPageDataByProject(ProjectEntity project);
	
	List<FormulaPageDataEntity> getFormulaPageDataByProjectResourceCode(ProjectEntity project, String resourceCode);
	
	List<FormulaPageDataEntity> getFormulaPageDataByProjectAndRange(ProjectEntity project, int start, int end);
	
	FormulaPageDataEntity getFormulaPageDataByProjectResourceCodeColumnName(ProjectEntity project, String resourceCode, String columnName);
	
	// insertion
	void insertFormulaPageData(ProjectEntity project, String resourceCode, String columnName, String value, String type, int fromResource);
	
	void insertFormulaPageData(FormulaPageDataEntity newFormulaPageData);
	
	// update
	void updateFormulaPageData(FormulaPageDataEntity updatedData);
	
	// deletion
	int deleteFormulaPageData(ProjectEntity project, String resourceCode, String column_name);
	
	int deleteFormulaPageDataByProjectResourceId(ProjectEntity project, String resourceCode);
	
	int deleteFormulaPageDataByProject(ProjectEntity project);
}
