package com.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormulaRoutes {
	//
	@RequestMapping("/formula/test")
	public void testFormula() {
		System.out.println("formula user");
	}
	//generate a formula table by selected rows and new columns
	@RequestMapping("/formula/{project_id}")
	public void createFormulaTable() {
		System.out.println("create formula table");
	}
	//add formula column
	@RequestMapping("/formula/add_column")
	public void addFormulaColumn() {
		System.out.println("add formula column");
	}
	// edit rows in columns
	@RequestMapping("/formula/edit_column")
	public void editRowInFormulaColumns() {
		System.out.println("edit row in formula columbs");
	}
}
