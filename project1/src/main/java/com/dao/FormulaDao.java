package com.dao;

import java.util.List;

import com.entity.FormulaEntity;
import com.entity.ProjectEntity;

public interface FormulaDao {
	
	// select
	List<FormulaEntity> getFormulaByProject(ProjectEntity project);
	
	// insert
	void insertFormula(ProjectEntity project, String formulaName, String formula);
	
	// delete
	int deleteFormula(ProjectEntity project, String formulaName);
	
	int deleteAllFormulas();
} 
