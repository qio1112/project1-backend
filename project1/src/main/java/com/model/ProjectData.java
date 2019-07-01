package com.model;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;

public class ProjectData {

	private ProjectEntity project;
	
	private DataEntity[] data;
	
	private ResourceEntity[] resource;
	
	public ProjectData() { }

	public ProjectData(ProjectEntity project, DataEntity[] data, ResourceEntity[] resource) {
		this.project = project;
		this.data = data;
		this.resource = resource;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public DataEntity[] getData() {
		return data;
	}

	public void setData(DataEntity[] data) {
		this.data = data;
	}

	public ResourceEntity[] getResource() {
		return resource;
	}

	public void setResource(ResourceEntity[] resource) {
		this.resource = resource;
	}

	
}
