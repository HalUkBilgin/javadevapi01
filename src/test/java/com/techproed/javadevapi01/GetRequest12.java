package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import pojos.Bookingdates;
import pojos.Bookings;
import testbaseclasses.TestBaseHerOkuApp;

public class GetRequest12 extends TestBaseHerOkuApp {
	
	/*
	 	When 
	 		I send GET Request to https://restful-booker.herokuapp.com/booking/1
	 	Then 
	 		Response body should be like that;
									 		{
											    "firstname": "Eric",
											    "lastname": "Smith",
											    "totalprice": 555,
											    "depositpaid": false,
											    "bookingdates": {
											        "checkin": "2016-09-09",
											        "checkout": "2017-09-21"
											     }
											}
	*/
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("booking", "booking", 
				        "id", 1);
		
		//Set expected data
		Bookingdates bookingdates = new Bookingdates("2015-09-15", "2018-10-02");
		Bookings expectedData = new Bookings("Mary", "Ericsson", 364, true, bookingdates);
		
		//Send the request
		Response response = given().spec(spec).when().get("/{booking}/{id}");
		response.prettyPrint();
		
		
		//Assertion: GSON + POJO
		Bookings actualData = response.as(Bookings.class);
		System.out.println(actualData);
		
		assertEquals(expectedData.getFirstname(), actualData.getFirstname());
		assertEquals(expectedData.getLastname(), actualData.getLastname());
		assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
		assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
		
		assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
		assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

	}

}
