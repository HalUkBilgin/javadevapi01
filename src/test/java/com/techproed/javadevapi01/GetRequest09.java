package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;

public class GetRequest09 extends TestBaseDummyRestApi {
	
	/*
	 	When 
			 I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
		 Then
			 Status code is 200
			 1)Print all ids greater than 10 on the console
			   Assert that there are 14 ids greater than 10
			 2)Print all ages less than 30 on the console
			   Assert that maximum age less than 30 is 23
			 3)Print all salaries greater than 100000 on the console 
			   Assert that minimum salary greater than 100000 is 103600 
			 4)Print all employee names whose salaries are greater than 350,000 
			   Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
	 */
	
	@Test
	public void get01() {
		
		spec.pathParams("api", "api",
				"version", "v1",
				"employees", "employees");

		Response response = given().spec(spec).when().get("/{api}/{version}/{employees}");
		
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		assertEquals(200, response.getStatusCode());
		
		//1)Print all ids greater than 10 on the console
		List<String> idList = json.getList("data.findAll{Integer.parseInt(it.id)>10}.id");
		System.out.println(idList);
		//Assert that there are 14 ids greater than 10
		assertEquals(14, idList.size());
		
		//2)Print all ages less than 30 on the console
		List<String> ageList = json.getList("data.findAll{Integer.parseInt(it.employee_age)<30}.employee_age");
        System.out.println(ageList); 
        
        List<Integer> ageListInt = new ArrayList<>();
        for(String w : ageList) {
        	ageListInt.add(Integer.parseInt(w));
        }
		
        Collections.sort(ageListInt);
        
		//Assert that maximum age less than 30 is 23
        assertEquals(23, (int)ageListInt.get(ageListInt.size()-1));
		
        //3)Print all salaries greater than 100000 on the console
        List<String> salaryList = json.getList("data.findAll{Integer.parseInt(it.employee_salary)>100000}.employee_salary");  
        
        List<Integer> salaryListInt = new ArrayList<Integer>();
        
        for(String w : salaryList) {
            salaryListInt.add(Integer.parseInt(w));
        }
        
        System.out.println(salaryListInt);
        Collections.sort(salaryListInt); 
        System.out.println(salaryListInt);
		
	    //Assert that minimum salary greater than 100000 is 103600
        assertEquals(103600, (int)salaryListInt.get(0));
        
        //4)Print all employee names whose salaries are greater than 350,000 
        List<String> nameList = json.getList("data.findAll{Integer.parseInt(it.employee_salary)>350000}.employee_name");
        
        System.out.println(nameList);

		//Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
		assertTrue(nameList.contains("Charde Marshall"));
			
	}
	
}

