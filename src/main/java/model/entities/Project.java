package model.entities;

import java.util.ArrayList;
/**
 * @author Nicholas Marsden
 *
 *  gets the id of the project accociated with it 
 *
 */
public class Project {
	int project_id;
	String ProjectNames;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getProjectNames() {
		return ProjectNames;
	}
	public void setProjectNames(String projectNames) {
		ProjectNames = projectNames;
	}
	

	
}
