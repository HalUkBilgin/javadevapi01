package com.techproed.javadevapi01;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest01 {
	
	 /*
	 	Gherkin Language: 
	 	Given: It declares prerequisetes 
	 	When: It defines the action which user will perform
	 	Then: Talk about outputs
	 	And: It can be used in "Given", "When", "Then", if you have multiple options
	 */
	
	/*
	 	 When 
		     I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3    
		 Then 
		     HTTP Status Code should be 200
		 And  
		     Content Type should be in JSON Format
		 And  
		     Status Line should be HTTP/1.1 200 OK
	*/
	
	@Test
	public void get01() {
		
		//1.Step: Set the URL
		String url = "https://restful-booker.herokuapp.com/booking/3";
		
		//2.Step: Set the expected data
		
		//3.Step: Send the request to the API 
		Response response = given().accept("application/json").when().get(url); //ContentType.JSON also works
		
		response.prettyPrint();
		
		//4.Step: Make assertions
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
		
		//How to print content-type, status code, status line, etc. on the console... 
		System.out.println(response.getContentType());
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeaders());
		System.out.println(response.getHeader("Date"));

	}

}
