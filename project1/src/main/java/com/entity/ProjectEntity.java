package com.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hibernate entity mapping to table "project"
 * @author yipeng
 *
 */
@Entity
@Table(name="project")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="project_name")
	private String projectName;
	
	public ProjectEntity() {
		System.out.println("Entity project created.");
	}
	
	public ProjectEntity(String name) {
		this.projectName = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + "]";
	}
	
}
