package com.techproed.javadevapi01;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testbaseclasses.TestBaseHerOkuApp;

public class GetRequest07 extends TestBaseHerOkuApp {
	
	/*
	  When 
	  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5 
	  Then 
		  HTTP Status Code should be 200
		  And response content type is “application/JSON” 
		  And response body should be like; 
		  { 
		  	"firstname": "Sally", 
		    "lastname": "Ericsson", 
		    "totalprice": 111,
		    "depositpaid": false, 
		    "bookingdates": { "checkin": "2017-05-23", 
		                      "checkout":"2019-07-02" }
		  }
	 */
	
	@Test
	public void get01() {
		
		//Set the URL
		spec.pathParams("bookingName", "booking", 
				        "idValue", 5);
		
		//Set the expected data
		
		//Send the Request
		Response response = given().spec(spec).when().get("/{bookingName}/{idValue}");
		
		response.prettyPrint();
		
		//Assert
		//1.Way: Use body()
//		response.
//			then().
//			assertThat().
//			statusCode(200).
//			body("firstname", equalTo("Sally"),
//				 "lastname", equalTo("Jackson"),
//				 "totalprice", equalTo(134),
//				 "depositpaid", equalTo(true),
//				 "bookingdates.checkin",equalTo("2020-03-20"),
//				 "bookingdates.checkout",equalTo("2020-10-25")
//				 );
		
		//2.Way: Use JsonPath Class + Hard Assertion
		//JsonPath Class is good to navigate and to assert for JSON Data
		JsonPath json = response.jsonPath();
		
//		assertEquals(200, response.getStatusCode());
//		assertEquals("Mark", json.getString("firstname"));
//		assertEquals("Ericsson", json.getString("lastname"));		
//		assertEquals(512, json.getInt("totalprice"));		
//		assertEquals(true, json.getBoolean("depositpaid"));		
//		assertEquals("2016-08-20", json.getString("bookingdates.checkin"));		
//		assertEquals("2018-04-12", json.getString("bookingdates.checkout"));
		
		//3.Way: Use JsonPath Class + Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(response.getStatusCode(), 200);
		softAssert.assertEquals(json.getString("firstname"), "Sally");
		softAssert.assertEquals(json.getString("lastname"), "Jones");
		softAssert.assertEquals(json.getInt("totalprice"), 529);
		softAssert.assertEquals(json.getBoolean("depositpaid"), false);
		softAssert.assertEquals(json.getString("bookingdates.checkin"), "2016-04-24");
		softAssert.assertEquals(json.getString("bookingdates.checkout"), "2020-03-21");

		softAssert.assertAll();
	}

}
