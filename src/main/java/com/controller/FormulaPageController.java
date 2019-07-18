package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.entity.ProjectEntity;
import com.exception.DataNotFoundException;
import com.model.FormulaPageData;
import com.service.DataService;
import com.service.FormulaPageDataService;

/**
 * controller of formula page
 * @author yipeng, Nicholaus
 *
 */
@RestController
@CrossOrigin(origins="*")
public class FormulaPageController {
	
	@Autowired 
	private DataService dataService;
	
	@Autowired
	private FormulaPageDataService formulaPageDataService;
	
	// get formula data by project name
	@GetMapping(value="/formula/{projectName}")
	@ResponseStatus(HttpStatus.OK)
	public FormulaPageData getFormulaPageDataByProjectName(@PathVariable String projectName) {
			
		// get project (id)
		ProjectEntity project = null;
		try {
			project = dataService.getProjectByName(projectName);
		} catch (DataNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.message());
		}
		
		// get formula data
		return formulaPageDataService.getFormulaPageDataByProject(project);
	}
		
	// create new formula of certain project
	@PutMapping(value="/formula/create_new_formula_page")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> createNewFormulaData(@RequestBody FormulaPageData newFormulaPage) {
		
		ProjectEntity project = newFormulaPage.getProject();
		
		try {
			// delete old data
			formulaPageDataService.deleteFormulaPageDataByProject(project);
			// insert new formula page data
			formulaPageDataService.insertFormulaPageData(newFormulaPage);
			
			return new ResponseEntity<>("formula data created", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
			
		}
	}
}
