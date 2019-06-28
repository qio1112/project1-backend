package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class DataEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="project_id")
	private ProjectEntity project;
	
	@Id
	@Column(name="resource_code")
	private String resourceCode;
	
	@Id
	@Column(name="column_name")
	private String columnName;
	
	@Column(name="value")
	private String value;
	
	@Column(name="type")
	private String type;
	
	public DataEntity() { }

	public DataEntity(ProjectEntity project, String resourceCode, String columnName, String value, String type) {
		super();
		this.project = project;
		this.resourceCode = resourceCode;
		this.columnName = columnName;
		this.value = value;
		this.type = type;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
