package com.techproed.javadevapi01;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import testbaseclasses.TestBaseHerOkuApp;

public class GetRequest06 extends TestBaseHerOkuApp {

	/*
	 	When 
	  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
	  	 Then
	  		Among the data there should be someone whose first name is "Susan" and last name is "Brown"
	*/
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.
			pathParam("bookingName", "booking").
			queryParams("firstname", "Mark", 
					    "lastname", "Ericsson");
		
		//Set the expected data
		
		//Send the request
		Response response = given().spec(spec).when().get("/{bookingName}");
		
		response.prettyPrint();
		
		//Assert
		response.then().assertThat().statusCode(200);
		
		assertTrue(response.asString().contains("bookingid"));

	}
	
}
