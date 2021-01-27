package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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
			 3)Print all employee names whose salaries are greater than 350,000 
			   Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
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
		
		//Print all ids greater than 10 on the console
		//Groovy Language
		List<String> idListGreaterThanTen = json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
		System.out.println(idListGreaterThanTen);
		//Assert that there are 14 ids greater than 10
		assertEquals(14, idListGreaterThanTen.size());
		
		//2)Print all ages less than 30 on the console
		List<String> idListLessThan30= json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
		System.out.println(idListLessThan30); 
		
		//Assert that maximum age less than 30 is 23
		List<Integer> idListLessThan30Int = new ArrayList<>();
		
		for(String w : idListLessThan30) {
			idListLessThan30Int.add(Integer.valueOf(w));
		}
		
		//Note: You cannot select maximum value among String elements.
		//      Because of that you need to convert all String elements to integer.
		
		Collections.sort(idListLessThan30);
		System.out.println(idListLessThan30);
		
		Collections.sort(idListLessThan30Int);
		System.out.println(idListLessThan30Int);
		 
		
		assertEquals(23, (int)idListLessThan30Int.get(idListLessThan30Int.size()-1));
		
		//3)Print all employee names whose salaries are greater than 350,000 
		
		
		
		
		
		
		//Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000

	}
	
}
