package com.routes;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.entities.FormulaDetails;

/**	
 * 
 * @author Nicholas Marsden
 *
 *All of these method could return back a message to the user with object turn into a string for data.
 *
 */

@RestController
@RequestMapping(value="/formula")
public class FormulaRoutes {
	@ResponseBody
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testFormula() {
		return "formula test successfull";
	}
	//generate a formula table by selected rows and new columns
	@RequestMapping(value="/createFormulaTable",method= RequestMethod.POST)
	public void createFormulaTable( @RequestBody  FormulaDetails formulaDetails) {
//		System.out.println(formulaDetails.getColumns());
		for(Map<String, ArrayList<Object>> column: formulaDetails.getColumns()) {
			System.out.print(column.keySet().toArray()[0] + " " + column.get(column.keySet().toArray()[0]));
			System.out.println();
		}
		
		System.out.println("create formula table");
	}
	
	
	//add formula column
	@RequestMapping(value="/add_column",method= RequestMethod.PUT)
	public void addFormulaColumn(@RequestBody  FormulaDetails formulaDetails) {
		//add column names
		// add column type
		// add formula 
		// everything else would be empty 
		// fills up the column name and column type and formula
		System.out.println("add formula column");
	}
	
	
	// edit rows in columns
	/**@param formulaDetails
	 * takes care of the removal and installation of items
	 */
	@RequestMapping(value="/edit_column",method= RequestMethod.PUT)
	public void editRowInFormulaColumns(@RequestBody  FormulaDetails formulaDetails) {
		// user can edit multiple rows in the column
		//so we have to work with the columns object which can update it rows base on 
		//the row which was selected from the columns (ArrayList<Map<String, ArrayList<Object>>> columns)
		System.out.println("edit row in formula columbs");
	}
	
}
