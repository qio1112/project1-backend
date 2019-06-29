package com.routes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class getPage {

	@RequestMapping("/add")
	public String helloWorld() {
		System.out.println("love you long time");
		return "nomouh";
	}
	
	
//    @GetMapping(value = "/student/{studentId}")
//    public @ResponseBody Student getTestData(@PathVariable Integer studentId) {
//        Student student = new Student();
//        student.setName("Peter");
//        student.setId(studentId);
// 
//        return student;
//    }
	
  @GetMapping(value = "/student")
  public  void getTestData() {
	  	System.out.println("Jamin with you to get my girl");
  }
}
