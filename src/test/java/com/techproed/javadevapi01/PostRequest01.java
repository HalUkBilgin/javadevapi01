package com.techproed.javadevapi01;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Bookings;
import pojos.Data;
import pojos.Employees;
import testbaseclasses.TestBaseDummyRestApi;

public class PostRequest01 extends TestBaseDummyRestApi{
	
	/*
	 	POST Request is used to create a new data in database
	 	
	 	POST Request needs; 1)Post Method 2)URL 3)Data 	
	*/
	
	/*
	 	 When
	 	  	I send a POST Request to the Url http://dummy.restapiexample.com/api/v1/create
	 	  	by using the following Request Body {
												    "name":"SULEYMAN ALP",
												    "salary":"1000",
												    "age":"33",
												    "profile_image": ""
												}
	 	 Then 
	 	  	Status code is 200
	 	  	And response body should be like {
											    "status": "success",
											    "data": {
											        "name": "SULEYMAN ALP",
											        "salary": "1000",
											        "age": "33",
											        "profile_image": null
											    },
											    "message": "Successfully! Record has been added."
											 }
	 */
	
	@Test
	public void post01() {
		//Set the URL
		spec.pathParams("api", "api", 
				        "version", "v1",
				        "create", "create");
		
		//Set the posted data
		//1.Way: Use constructor without parameter
//		Data postedData = new Data();
//		postedData.setEmployeeName("Suleyman Alp");
//		postedData.setEmployeeSalary(1000);
//		postedData.setEmployeeAge(33);
//		postedData.setProfileImage("");
		
		//2.Way: Use constructor with parameter and type null for id
		Data postedData = new Data(0, "Suleyman Alp", 1000, 33, "");
		if(postedData.getProfileImage().equals("")) {
			postedData.setProfileImage(null);
		}
		
		//3.Way: Use Map
//		HashMap<String, Object> postedData = new HashMap<>();
//		postedData.put("employee_name", "Suleyman Alp");
//		postedData.put("employee_salary", 1000);
//		postedData.put("employee_age", 33);
//		postedData.put("profile_image", "");
		
		System.out.println(postedData);
		
		//Send POST Request
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								auth().
								basic("admin", "password123").
								body(postedData).
							when().
							    post("/{api}/{version}/{create}");
		response.prettyPrint();
		
		//Assert
		Employees actualData = response.as(Employees.class);
		System.out.println(actualData);
		
		assertEquals(postedData.getEmployeeName(), actualData.getData().getEmployeeName());
		assertEquals(postedData.getEmployeeSalary(), actualData.getData().getEmployeeSalary());
		assertEquals(postedData.getEmployeeAge(), actualData.getData().getEmployeeAge());
		assertEquals(postedData.getProfileImage(), actualData.getData().getProfileImage());
		
	}

}
