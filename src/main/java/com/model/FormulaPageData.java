package com.model;

import com.entity.FormulaEntity;
import com.entity.FormulaPageDataEntity;
import com.entity.ProjectEntity;

/**
 * json mapping class to receiving and sending json through jackson
 * maps to data of formula page, including non-formula data and formulas
 * @author yipeng
 *
 */
public class FormulaPageData {
	
	private ProjectEntity project;
	
	private FormulaPageDataEntity[] formulaPageData;
	
	private FormulaEntity[] formulas;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public FormulaPageData() { }

	public FormulaPageData(ProjectEntity project, FormulaPageDataEntity[] formulaPageData, FormulaEntity[] formulas) {
		this.project = project;
		this.formulaPageData = formulaPageData;
		this.formulas = formulas;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public FormulaPageDataEntity[] getFormulaPageData() {
		return formulaPageData;
	}

	public void setFormulaPageData(FormulaPageDataEntity[] formulaPageData) {
		this.formulaPageData = formulaPageData;
	}

	public FormulaEntity[] getFormulas() {
		return formulas;
	}

	public void setFormulas(FormulaEntity[] formulas) {
		this.formulas = formulas;
	}

}
