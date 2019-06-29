package model.entities;

import java.util.ArrayList;
import java.util.Map;
/**
 * 
 * @author Nicholas Marsden
 *getting all the information from a formula
 *
 *
 */
public class FormulaDetails {
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
	
	
	
	
	
	
	
	
	
	
	public int getProduct_id() {
		return product_id;
	}
	public ArrayList<String> getColumn_added() {
		return column_added;
	}
	public ArrayList<String> getColumn_delete() {
		return column_delete;
	}
	
	public ArrayList<String> getResource_code() {
		return resource_code;
	}
	public ArrayList<String> getColumn_type() {
		return column_type;
	}
	public ArrayList<Map<String, ArrayList<Object>>> getColumns() {
		return columns;
	}
	public ArrayList<Boolean> getFrom_resource() {
		return from_resource;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setColumn_added(ArrayList<String> column_added) {
		this.column_added = column_added;
	}
	public void setColumn_delete(ArrayList<String> column_delete) {
		this.column_delete = column_delete;
	}

	public void setResource_code(ArrayList<String> resource_code) {
		this.resource_code = resource_code;
	}
	public ArrayList<Map<String, String>> getFormula() {
		return formula;
	}
	public void setFormula(ArrayList<Map<String, String>> formula) {
		this.formula = formula;
	}
	public void setColumns(ArrayList<Map<String, ArrayList<Object>>> columns) {
		this.columns = columns;
	}

	public void setColumn_type(ArrayList<String> column_type) {
		this.column_type = column_type;
	}
	public void setFrom_resource(ArrayList<Boolean> from_resource) {
		this.from_resource = from_resource;
	}
	
}

/*
 * Methods to get Each column from the user
 * OR
 * store each column from the database
 * 
 * */
