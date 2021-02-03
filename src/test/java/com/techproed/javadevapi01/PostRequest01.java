package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Data;
import pojos.Employees;
import testbaseclasses.TestBaseDummyRestApi;

public class PostRequest01 extends TestBaseDummyRestApi{
	
	/*
	 	POST Request is used to create new data in database.
	 	
	 	For POST Request we need;
	 	1)POST Method
	 	2)URL
	 	3)Request body
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
		
		//Set the posted body
		//1.Way
//		Data postedData = new Data(0, "SULEYMAN ALP", 1000, 33, "");
		
		//2.Way
		Data postedData = new Data();
		postedData.setEmployeeName("SULEYMAN ALP");
		postedData.setEmployeeAge(33);
		postedData.setEmployeeSalary(1000);
		postedData.setProfileImage("");
		
		//3.Way: You can create a new constructor which does not have id as parameter
		
		//4.Way: You can use HashMap to create posted data
		
		System.out.println(postedData);
		//API is converting "" to null because of that I typed the following code
		if(postedData.getProfileImage().equals("")) {
			postedData.setProfileImage(null);
		}
		
		//Send POST Request
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								body(postedData).
							when().
								post("/{api}/{version}/{create}");
		response.prettyPrint();
		
		//Assertion: GSON + POJO
		Employees actualData = response.as(Employees.class);
		System.out.println(actualData);
		
		assertEquals(postedData.getEmployeeName(), actualData.getData().getEmployeeName());
		assertEquals(postedData.getEmployeeSalary(), actualData.getData().getEmployeeSalary());
		assertEquals(postedData.getEmployeeAge(), actualData.getData().getEmployeeAge());
		assertEquals(postedData.getProfileImage(), actualData.getData().getProfileImage());
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
