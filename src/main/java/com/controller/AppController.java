package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.entity.UserEntity;
import com.model.FormulaPageData;
import com.model.ProjectData;
import com.service.DataService;
import com.service.FormulaPageDataService;
import com.service.UserService;

/**
 * 
 * @author Nicholas Marsden, yipeng
 *
 */
@RestController
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private DataService dataService;
	
	@Autowired
	private FormulaPageDataService formulaPageDataService;
	
	@Autowired 
	private SessionFactory sessionFactory;
	
	// test login
//	@PostMapping(value="/auth/login")
//	public String login(@RequestBody UserEntity user, HttpServletResponse response) {
//		
//		String jwtoken = userService.createUserToken(user.getUsername(), user.getPassword() ) ;
////		System.out.print("jwt : "+ jwtoken);
//		response.addHeader("token", jwtoken);
//		System.out.println("added token to the browser head for user");
//		return jwtoken;
//	}
//	
//	// create new user
//	@PostMapping(value="/auth/signup")
//	@ResponseStatus(HttpStatus.OK)
//	public UserEntity signup(@RequestBody UserEntity newUser) {
//		
//		userService.signup(newUser);
//		return newUser;
//	}
//	
//	// get all projects
//	@GetMapping(value="/project/projects")
//	@ResponseStatus(HttpStatus.OK)
//	public List<ProjectEntity> getProjects() {
//		
//		return dataService.getProjects();
//	}
//	
//	// project data 
//	
//	// insert csv file
//	@PostMapping(value="/project/save_file")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void insertFromFile(@RequestParam("file") MultipartFile csvFile) throws IOException{
//		String projectName = csvFile.getOriginalFilename().split("\\.")[0] + new Date().getTime();
//		System.out.println(projectName);
//		BufferedReader reader = null;
//		ProjectEntity project = dataService.createProject(projectName);
//		
//		try {
//			// first two columns will always be called resource_name and resource_code
//			reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
//			String[] columns = reader.readLine().split(",");
////			columns[0] = "resource_code";
////			columns[1] = "resource_name";
//			
//			System.out.println(Arrays.deepToString(columns));
//			
//			String valueString = reader.readLine();
//			String[] values = null;
//			if(valueString != null && valueString.length() != 0)
//				values = valueString.split(",");
//			
//			ProjectData newProjectData = new ProjectData();
//			List<DataEntity> dataEntities = new ArrayList<>();
//			List<ResourceEntity> resourceEntities = new ArrayList<>();
//			
//			while(valueString != null && valueString.length() > 0) {
////				System.out.println(valueString);
//				resourceEntities.add(new ResourceEntity(project, values[0], values[1]));
//				for(int i = 2; i < values.length; i ++) {
//					String curValue = values[i];
//					String type = getType(curValue);
//					dataEntities.add(new DataEntity(project, values[0], columns[i], values[i], type));
//				}
//				valueString = reader.readLine();
//				if(valueString != null && valueString.length() != 0)
//					values = valueString.split(",");
//				
//			}
//			newProjectData.setProject(project);
//			newProjectData.setData(dataEntities.toArray(new DataEntity[dataEntities.size()]));
//			newProjectData.setResource(resourceEntities.toArray(new ResourceEntity[resourceEntities.size()]));
//			
//			dataService.insertDataToProject(newProjectData);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if(reader != null)
//				reader.close();
//		}
//	}
//	
//	// get data by project name, page number, and number of rows on each row
//	@GetMapping(value="/project/{projectName}?page={page}&page_rows={numPerPage}")
//	@ResponseStatus(HttpStatus.OK)
//	public ProjectData getProjectDataByProjectNameAngPage(@PathVariable String projectName, @PathVariable int page, @PathVariable int numPerPage) {
//		
//		return dataService.getProjectDataByNameAngPage(projectName, page, numPerPage);
//	}
//	
//	// update one row (maybe one cell)
//	@PatchMapping(value="/project/{projectName}/edit_row")
//	@ResponseStatus(HttpStatus.OK)
//	public void updateProjectDataCell(DataEntity newData) {
//		
//		dataService.updataData(newData);
//	}
//	
//	// add new column to resource data
//	@PatchMapping(value="/project/add_column/{columnName}")
//	@ResponseStatus(HttpStatus.OK)
//	public void addColumnToProject(@RequestBody ProjectEntity project, @PathVariable String columnName) {
//		
//		// use resource to find all resource_code of this project
//		List<ResourceEntity> resourceOfProject = dataService.getResourceByProject(project);
//		// add new rows in data table to each resource_code add new column of every row
//		Iterator<ResourceEntity> itr = resourceOfProject.iterator();
//		while(itr.hasNext()) {
//			ResourceEntity curResource = itr.next();
//			dataService.insertDataToData(new DataEntity(project, curResource.getResourceCode(), columnName, null, null));
//		}
//	}
//	
//	// delete project data column
//	@PatchMapping(value="/project/delete_column/{columnName}")
//	@ResponseStatus(HttpStatus.OK)
//	public void deleteColumnFromProject(@RequestBody ProjectEntity project, @PathVariable String columnName) {
//		
//		// delete rows in data table with project and columnName
//		dataService.deleteColumnOfProject(project, columnName);
//	}
//	
//	
//	// formula data
//	
//	// get formula data by project name
//	@GetMapping(value="/formula/{projectName}")
//	@ResponseStatus(HttpStatus.OK)
//	public FormulaPageData getFormulaPageDataByProjectName(@PathVariable String projectName) {
//		
//		// get project (id)
//		ProjectEntity project = dataService.getProjectByName(projectName);
//		
//		// get formula data
//		return formulaPageDataService.getFormulaPageDataByProject(project);
//	}
//	
//	// create new formula of certain project
//	@PutMapping(value="/formula/create_new_formula_page")
//	@ResponseStatus(HttpStatus.OK)
//	public void createNewFormulaData(@RequestBody ProjectEntity project, @RequestBody FormulaPageData formulaPage) {
//		
//		// delete old data
//		formulaPageDataService.deleteFormulaPageDataByProject(project);
//		
//		// insert new formula page data
//		formulaPageDataService.insertFormulaPageData(formulaPage);
//	}
//	
//	// add new non-formula column
////	@PatchMapping(value="/formula/add_column/{columnName}")
////	@ResponseStatus(HttpStatus.OK)
////	public void addColumnToFormulaPage(@RequestBody ProjectEntity project, @PathVariable String columnName) {
////		
////		// get all distinct resource_code of the project in formula_page_data table
////		
////		// add rows for each resource_code
////		
////	}
////	
////	// add new formula (formula operation)
////	@PatchMapping(value="/formula/add_formula")
////	@ResponseStatus(HttpStatus.OK)
////	public void addFormulaToFormulaPage(@RequestBody String newFormula) {
////		
////		
////	}
////	
////	// update a non-formula data row
////	
////	
////	// update a formula
////	
////	
////	
////	// delete row
////	@PatchMapping(value="/formula/delete_row/{resourceCode}")
////	@ResponseStatus(HttpStatus.OK)
////	public void deleteRowFromFormulaPage(@RequestBody ProjectEntity project, @PathVariable String resourceCode) {
////		
////		formulaPageDataService.deleteFormulaPageRow(project, resourceCode);
////	}
////	
////	// delete a non-formula generated column
////	@PatchMapping(value="/formula/delete_column/{columnName}")
////	@ResponseStatus(HttpStatus.OK)
////	public void deleteColumnFromFormulaPage(@RequestBody ProjectEntity project, @PathVariable String columnName) {
////		
////		formulaPageDataService.deleteFormulaPageColumn(project, columnName);
////	}
////	
	@GetMapping(value="/test")
	@ResponseStatus(HttpStatus.OK)
	public UserEntity test() {
		System.out.println(this.sessionFactory == null);
		return new UserEntity("myusername", "mypassword", "yipeng", new Date());
	}
	
	// get data type, number or text
	private String getType(String data) {
		String result = "number";
		try {
			Double.parseDouble(data);
		} catch(Exception e) {
			result = "text";
		}
		return result;
	}
}
