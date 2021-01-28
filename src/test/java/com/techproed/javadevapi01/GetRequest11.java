package com.techproed.javadevapi01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.sun.xml.xsom.impl.scd.Iterators.Map;

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
	 		And 40, 21, and 19 are among the ages
	 		And 11th employee is like {
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
		
		//Send the request
		Response response = given().spec(spec).when().get("/{api}/{version}/{employees}");
		
		//Assertion
		HashMap<String, Object> actualData = response.as(HashMap.class);
		System.out.println(actualData);
		
		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
		
		//5th employee name is Airi Satou		
		assertEquals(expectedData.get("fifthEmployee"), ((HashMap)((List)actualData.get("data")).get(4)).get("employee_name"));
		
		//Number of employees is 24
		assertEquals(expectedData.get("numOfEmployee"), ((List)actualData.get("data")).size());
		
		//Salary of 2nd last employee is 170750
		assertEquals(expectedData.get("secondSalary"), ((HashMap)((List)actualData.get("data")).get(1)).get("employee_salary"));
		
		//40, 21, and 19 are among the ages
		List<String> allAgeList = new ArrayList<>(); 
		for(int i=0; i<((List)actualData.get("data")).size(); i++) {
			allAgeList.add((String) ((HashMap)((List)actualData.get("data")).get(i)).get("employee_age"));
		}
		assertTrue(allAgeList.containsAll((List)expectedData.get("ages")));
		
		//11th employee is ...
		assertEquals(expectedData.get("eleventhEmployee"), ((List)actualData.get("data")).get(10));
	}

}
