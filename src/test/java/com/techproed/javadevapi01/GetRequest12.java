package com.techproed.javadevapi01;

public class GetRequest12 {
	
	/*
	 	POJO: Plain Old Java Objects
	 	
	 	POJO(Bean) should contain;
	 	
	 	1)private variables
	 	2)Constructor without parameter, constructor with all parameters
	 	3)All getters() and all setters()
	 	4)toString()
	 */
	
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

}
