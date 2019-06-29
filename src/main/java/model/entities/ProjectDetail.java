package model.entities;

import java.util.ArrayList;
import java.util.Map;


/**
 * 
 * @author Nicholas Marsden
 *
 *This is for the information 
 *in the column for one project
 *
 *
 */

public class ProjectDetail {
// in one project we have 
	int project_id;
	
	// many rows 
	ArrayList<String> resource_code;
	// many name
	ArrayList<String> resource_name;
	//many single columns with alot of rows
	ArrayList<Map<String,ArrayList<String>>> columns;	
}
