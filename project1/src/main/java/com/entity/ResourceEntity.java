package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="resource")
public class ResourceEntity {
	
	@OneToOne
	@JoinColumn(name="project_id")
	private ProjectEntity project;
	
	@Column(name="resource_code")
	private String resourceCode;
	
	@Column(name="resource_name")
	private String resourceName;
	
	public ResourceEntity() { }

	public ResourceEntity(ProjectEntity project, String resourceCode, String resourceName) {
		super();
		this.project = project;
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
