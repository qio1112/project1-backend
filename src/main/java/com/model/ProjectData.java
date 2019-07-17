package com.model;

import java.util.Arrays;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;

/**
 * json mapping class to receiving and sending json through jackson
 * maps to data of resource(project) page
 * @author yipeng
 *
 */
public class ProjectData {

	private ProjectEntity project;
	
	private DataEntity[] data;
	
	private ResourceEntity[] resource;
	
	@Override
	public String toString() {
		return "ProjectData [project=" + project + ", data=" + Arrays.toString(data) + ", resource="
				+ Arrays.toString(resource) + "]";
	}

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
