package com.techproed.javadevapi01;

import java.util.HashMap;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.response.Response;
import testbaseclasses.TestBaseJsonPlaceHolder;
import utilities.JsonUtil;

public class ObjectMapper01 extends TestBaseJsonPlaceHolder {

	/*
	 	When 
	 		I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198
	 		
	 	Then 
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
	*/
	
	@Test
	public void get01() {
		//Set the URL
		spec.pathParams("todos", "todos",
				        "id", 198);
		
		//Set expected data
		String expectedJson = "{\n"
							  + "\"userId\": 10,\n"
							  + "\"id\": 198,\n"
							  + "\"title\": \"quis eius est sint explicabo\",\n"
							  + "\"completed\": true\n"
							  + "}";
		
		HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(expectedJson, HashMap.class);
		System.out.println(expectedData);
		
		//Send the request
		Response response = given().spec(spec).when().get("/{todos}/{id}");
		HashMap<String, Object> actualData = JsonUtil.convertJsonToJava(response.asString(), HashMap.class);
		System.out.println(actualData);
		
		//Assert
		assertEquals(expectedData.get("completed"), actualData.get("completed"));
		assertEquals(expectedData.get("title"), actualData.get("title"));
		assertEquals(expectedData.get("userId"), actualData.get("userId"));

	}
}
