package com.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Hibernate entity mapping to table "resource"
 * @author yipeng
 *
 */
@Entity
@Table(name="resource")
public class ResourceEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name="project_id")
	private ProjectEntity project;
	
	@Id
	@Column(name="resource_code")
	private String resourceCode;
	
	@Column(name="resource_name")
	private String resourceName;
	
	public ResourceEntity() { }

	public ResourceEntity(ProjectEntity project, String resourceCode, String resourceName) {
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
