package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import pojos.Data;
import pojos.Employees;
import testbaseclasses.TestBaseDummyRestApi;

public class GetRequest13 extends TestBaseDummyRestApi{

	/*
 	    When 
	 		I send GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
	 	Then 
	 		Status code is 200
	 		And response body is like {
									    "status": "success",
									    "data": {
									        "id": 1,
									        "employee_name": "Tiger Nixon",
									        "employee_salary": 320800,
									        "employee_age": 61,
									        "profile_image": ""
									    },
									    "message": "Successfully! Record has been fetched."
									   }
	 */
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("api", "api",
				        "version", "v1",
				        "employee", "employee",
				        "id", 1);
		
		//Set the expected data
		Data data = new Data(1, "Tiger Nixon", 320800, 61, "");
		Employees expectedData = new Employees("success", data, "Successfully! Record has been fetched.");
		
		//Send Request
		Response response = given().spec(spec).when().get("/{api}/{version}/{employee}/{id}");
		response.prettyPrint();
		
		//Assertion
		Employees actualData = response.as(Employees.class);
		System.out.println(actualData);
		
		assertEquals(expectedData.getStatus(), actualData.getStatus());
		assertEquals(expectedData.getMessage(), actualData.getMessage());
		
		assertEquals(expectedData.getData().getEmployeeName(), actualData.getData().getEmployeeName());
		assertEquals(expectedData.getData().getEmployeeSalary(), actualData.getData().getEmployeeSalary());
		assertEquals(expectedData.getData().getEmployeeAge(), actualData.getData().getEmployeeAge());
		assertEquals(expectedData.getData().getProfileImage(), actualData.getData().getProfileImage());
	
	}
}
