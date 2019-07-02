package com.service;

import com.entity.ProjectEntity;
import com.model.FormulaPageData;

/**
 * service for formula page data
 * @author yipeng
 *
 */
public interface FormulaPageDataService {
	
	FormulaPageData getFormulaPageDataByProject(ProjectEntity project);
	
	void insertFormulaPageData(FormulaPageData newFormulaPage);
	
//	void updateFormulaPageData(FormulaPageDataEntity formulaPageDataEntity);
	
	void updateFormulaPageData(FormulaPageData newPormulaPage);
	
	int deleteFormulaPageDataByProject(ProjectEntity project);
	
	int deleteFormulaPageColumn(ProjectEntity project, String columnName);
	
	int deleteFormulaPageRow(ProjectEntity project, String resourceCode);
}
