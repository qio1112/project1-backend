package model.entities;

import java.util.ArrayList;
import java.util.Map;

public class Formula {
	// project id
	int product_id;
	// gets the list of columns and resources 
	ArrayList<String> column_added;
	ArrayList<String> column_delete;
	ArrayList<String> resource_code;
	// get an array of formula mapping peer
	ArrayList<Map<String, String>> formula;
	// gets the column name and the row associated with it
	ArrayList<Map<String, ArrayList<Object>>> columns;
	// get the type of the columns
	ArrayList<String> column_type;
	//checks to see if column is from resource or not
	ArrayList<Boolean>  from_resource;
	
}

/*
 * Methods to get Each column from the user
 * OR
 * store each column from the database
 * 
 * */
