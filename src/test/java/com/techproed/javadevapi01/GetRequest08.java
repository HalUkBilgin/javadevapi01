package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;

public class GetRequest08 extends TestBaseDummyRestApi{
	
	/*
	 	When 
	 		I send Get Request to http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		The Status code is 200
	 		And The name of 3rd employee is "Ashton Cox"
	 		And The Salary of 6th employee is 372000
	 		And The age of the last employee is 23
	 		And 21, 23, 61 are among the ages
	*/
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("apiName", "api",
						"Version", "v1",
						"Employees", "employees");
		
		//Set the expected data
		
		//Send the request
		Response response = given().spec(spec).when().get("/{apiName}/{Version}/{Employees}");
		
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		assertEquals(200, response.getStatusCode());
		
		assertEquals("Ashton Cox", json.getString("data[2].employee_name"));
		
		assertEquals("372000", json.getString("data[5].employee_salary"));
		
		assertEquals("23", json.getString("data[-1].employee_age"));
		
		assertEquals("21", json.getString("data[-2].employee_age"));
		
		List<String> ageList = new ArrayList<>();
		ageList.add("21");
		ageList.add("61");
		ageList.add("23");
		
		assertTrue(json.getList("data.employee_age").containsAll(ageList));
		
		System.out.println(json.getList("data.employee_age"));
		System.out.println(json.getList("data.employee_salary"));

	}

}
