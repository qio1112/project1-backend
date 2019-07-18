package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.entity.DataEntity;
import com.entity.ProjectEntity;
import com.entity.ResourceEntity;
import com.exception.DataNotFoundException;
import com.model.ProjectData;
import com.service.DataService;

@RestController
@CrossOrigin(origins="*")
public class ProjectController {
	
	@Autowired 
	private DataService dataService;
	
	// get all projects
	@GetMapping(value="/project/projects")
	public Map<String, Object> getProjects() {
		
		try {
			List<ProjectEntity> projects = dataService.getProjects();
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("message", "projects fetched");
			resMap.put("data", projects);
			return resMap;
		} catch (DataNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.message());
		}
	}
	
	// project data 
	
	// insert csv file
	@PostMapping(value="/project/save_file")
//	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> insertFromFile(@RequestParam("file") MultipartFile csvFile) throws IOException{
		String projectName = csvFile.getOriginalFilename().split("\\.")[0] + new Date().getTime();
		System.out.println(projectName);
		BufferedReader reader = null;
		ProjectEntity project = dataService.createProject(projectName);
		
		try {
			// first two columns will always be called resource_name and resource_code
			reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
			String[] columns = reader.readLine().split(",");
//				columns[0] = "resource_code";
//				columns[1] = "resource_name";
			
			System.out.println(Arrays.deepToString(columns));
			
			String valueString = reader.readLine();
			String[] values = null;
			if(valueString != null && valueString.length() != 0)
				values = valueString.split(",");
			
			ProjectData newProjectData = new ProjectData();
			List<DataEntity> dataEntities = new ArrayList<>();
			List<ResourceEntity> resourceEntities = new ArrayList<>();
			
			while(valueString != null && valueString.length() > 0) {
//					System.out.println(valueString);
				resourceEntities.add(new ResourceEntity(project, values[0], values[1]));
				for(int i = 2; i < values.length; i ++) {
					String curValue = values[i];
					String type = getType(curValue);
					dataEntities.add(new DataEntity(project, values[0], columns[i], values[i], type));
				}
				valueString = reader.readLine();
				if(valueString != null && valueString.length() != 0)
					values = valueString.split(",");
				
			}
			newProjectData.setProject(project);
			newProjectData.setData(dataEntities.toArray(new DataEntity[dataEntities.size()]));
			newProjectData.setResource(resourceEntities.toArray(new ResourceEntity[resourceEntities.size()]));
			
			dataService.insertDataToProject(newProjectData);
			Map<String, Object> res = new HashMap<>();
			res.put("message", "file uploaded");
			res.put("data", dataService.getProjectByName(projectName));
			return res;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload file");
		} finally {
			if(reader != null)
				reader.close();
		}
		
	}
	
	// get data by project name, page number, and number of rows on each row
	@GetMapping(value="/project/{projectName}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectData getProjectDataByProjectNameAngPage(@PathVariable String projectName, @RequestParam(name="page") int page, @RequestParam(name="page_rows") int numPerPage) {
		
		try {
			return dataService.getProjectDataByNameAngPage(projectName, page, numPerPage);
		} catch (DataNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.message());
		}
	}
	
	// update one row (maybe one cell)
	@PatchMapping(value="/project/update_cell")
	@ResponseStatus(HttpStatus.OK)
	public void updateProjectDataCell(@RequestBody DataEntity newData) {
		
//		System.out.println(newData);
		dataService.updataData(newData);
	}
	
	// add new column to resource data
	@PatchMapping(value="/project/add_column/{columnName}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> addColumnToProject(@RequestBody ProjectEntity project, @PathVariable String columnName) {
		
		try  {
			// use resource to find all resource_code of this project
			List<ResourceEntity> resourceOfProject = dataService.getResourceByProject(project);
			// add new rows in data table to each resource_code add new column of every row
			Iterator<ResourceEntity> itr = resourceOfProject.iterator();
			while(itr.hasNext()) {
				ResourceEntity curResource = itr.next();
				dataService.insertDataToData(new DataEntity(project, curResource.getResourceCode(), columnName, null, "text"));
			}
			return new ResponseEntity<>("column added", HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
		}
			
	}
	
	// delete project data column
	@PatchMapping(value="/project/delete_column/{columnName}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> deleteColumnFromProject(@RequestBody ProjectEntity project, @PathVariable String columnName) {
		
		try {
			// delete rows in data table with project and columnName
			dataService.deleteColumnOfProject(project, columnName);
			return new ResponseEntity<>("column deleted", HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.NOT_ACCEPTABLE);
		}
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
