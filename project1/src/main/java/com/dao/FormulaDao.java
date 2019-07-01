package com.dao;

import java.util.List;

import com.entity.FormulaEntity;
import com.entity.ProjectEntity;

/**
 * DAO of formulas in formula pages
 * @author yipeng
 *
 */
public interface FormulaDao {
	
	// select
	List<FormulaEntity> getFormulaByProject(ProjectEntity project);
	
	// insert
	void insertFormula(ProjectEntity project, String formulaName, String formula);
	
	void insertFormula(FormulaEntity newFormula);
	
	void updateFormula(FormulaEntity newFormula);
	
	// delete
	int deleteFormula(ProjectEntity project, String formulaName);
	
	int deleteAllFormulas();
} 
