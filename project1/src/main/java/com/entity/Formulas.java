package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="formulas")
public class Formulas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="project_id")
	private ProjectEntity project;
	
	@Id
	@Column(name="formula_name")
	private String formulaName;
	
	@Column(name="formula")
	private String formula;

	public Formulas() { }

	public Formulas(ProjectEntity project, String formulaName, String formula) {
		super();
		this.project = project;
		this.formulaName = formulaName;
		this.formula = formula;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Override
	public String toString() {
		return "Formulas [project=" + project + ", formulaName=" + formulaName + ", formula=" + formula + "]";
	}
	
	
}
