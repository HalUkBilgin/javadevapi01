package com.techproed.javadevapi01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseDummyRestApi;
import testdata.TestDataDummyRestApi;

public class GetRequest11 extends TestBaseDummyRestApi {
	
	/*
	 	When
	 		I send GET Request to the Url http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		Status code is 200
	 		And 5th employee name is Airi Satou
	 		And the number of employees is 24
	 		And the salary of 2nd last employee is 170750
	 		======> And 40, 21, and 19 are among the ages
	 		======> And 11th employee is like {
									            "id": "11",
									            "employee_name": "Jena Gaines",
									            "employee_salary": "90560",
									            "employee_age": "30",
									            "profile_image": ""
		        							   }
	*/
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("api", "api",
				        "version", "v1",
				        "employees", "employees");
		
		//Set the expected data
		TestDataDummyRestApi obj = new TestDataDummyRestApi();
		HashMap<String, Object> expectedData = obj.setUpData();
		
		System.out.println(expectedData);
		
		//Send Request
		Response response = given().spec(spec).when().get("/{api}/{version}/{employees}");
		response.prettyPrint();

		//Assert
		//1.Way: JsonPath Class + expectedData Map
//		JsonPath json = response.jsonPath();
//		
//		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
//		assertEquals(expectedData.get("fifthEmpName"), json.getString("data[4].employee_name"));
//		assertEquals(expectedData.get("numOfEmployees"), json.getList("data").size());
//		assertEquals(expectedData.get("secondLastSalary"), json.getString("data[-2].employee_salary"));
//		assertTrue(json.getList("data.employee_age").containsAll((List)expectedData.get("ages")));
//		
//		assertTrue(json.getString("data[10].id").equals(((HashMap)expectedData.get("eleventhEmployee")).get("id")));
//		assertTrue(json.getString("data[10].employee_name").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_name")));
//		assertTrue(json.getString("data[10].employee_salary").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_salary")));
//		assertTrue(json.getString("data[10].employee_age").equals(((HashMap)expectedData.get("eleventhEmployee")).get("employee_age")));
//		assertTrue(json.getString("data[10].profile_image").equals(((HashMap)expectedData.get("eleventhEmployee")).get("profile_image")));

		//2.Way: GSON + expectedData Map
		HashMap<String, Object> actualData = response.as(HashMap.class);
		System.out.println(actualData);
		
		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
		
		assertEquals(expectedData.get("fifthEmpName"), ((HashMap)((List)actualData.get("data")).get(4)).get("employee_name"));
		
		int size = ((List)actualData.get("data")).size();
		assertEquals(expectedData.get("secondLastSalary"), ((HashMap)((List)actualData.get("data")).get(size-2)).get("employee_salary"));

		assertEquals(expectedData.get("numOfEmployees"), ((List)actualData.get("data")).size());
		
		List<String> allAgesList = new ArrayList<>();
		
		for(int i=0; i<((List)actualData.get("data")).size(); i++) {
			allAgesList.add((String)((HashMap)((List)actualData.get("data")).get(i)).get("employee_age"));
		}	
		assertTrue(allAgesList.containsAll((List)expectedData.get("ages")));
		
		assertEquals(expectedData.get("eleventhEmployee"), ((List)actualData.get("data")).get(10));

	}

}
