package com.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/formula")
public class FormulaRoutes {
	@ResponseBody
	@RequestMapping(value="/test", method= RequestMethod.GET)
	public String testFormula() {
		return "formula test successfull";
	}
	//generate a formula table by selected rows and new columns
	@RequestMapping(value="/{project_id}",method= RequestMethod.POST)
	public void createFormulaTable() {
		System.out.println("create formula table");
	}
	//add formula column
	@RequestMapping(value="/add_column",method= RequestMethod.PUT)
	public void addFormulaColumn() {
		System.out.println("add formula column");
	}
	// edit rows in columns
	@RequestMapping(value="/edit_column",method= RequestMethod.PUT)
	public void editRowInFormulaColumns() {
		System.out.println("edit row in formula columbs");
	}
}
