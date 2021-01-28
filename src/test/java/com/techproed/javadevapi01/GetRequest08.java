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

public class GetRequest08 extends TestBaseDummyRestApi {
	
	/*
	 	When 
	 		I send Get Request to http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		The Status Code is 200
	 		And The name of 3rd employee is "Ashton Cox"
	 		And The Salary of 6th employee is 372000
	 		And The age of the last employee is 23
	 		And 21, 23, 61 are among the ages
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
		
		//The name of 3rd employee is "Ashton Cox"
		assertEquals("Ashton Cox", json.getString("data[2].employee_name"));
		
		//The Salary of 6th employee is 372000
		assertEquals("372000", json.getString("data[5].employee_salary"));
		
		//The age of the last employee is 23
		assertEquals("23", json.getString("data[-1].employee_age"));
		
		//The age of the second last employee is 21
		assertEquals("21", json.getString("data[-2].employee_age"));
		
		//21, 23, 61 are among the ages
		List<String> ageList = new ArrayList<>();
		ageList.add("21");
		ageList.add("23");
		ageList.add("61");

		assertTrue(json.getList("data.employee_age").containsAll(ageList));

	}

}
