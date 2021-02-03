package com.techproed.javadevapi01;

import java.util.HashMap;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Todos;
import testbaseclasses.TestBaseJsonPlaceHolder;

public class PatchRequest01 extends TestBaseJsonPlaceHolder{

	/*
	   When
	 		I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "title": "Tidy your room"
										   }
	   Then 
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 10,
									    "title": "Tidy your room",
									    "completed": true,
									    "id": 198
									  }
	 */
	
	@Test
	public void patch01() {
		//Set the URL
		spec.pathParams("todos", "todos", 
		                "id", 198);
		
		//Set the Patch Request Body
		HashMap<String, String> patchReqBody = new HashMap<>();
		patchReqBody.put("title", "Tidy your room");
		
		HashMap<String,Object> others = new HashMap<>();
		others.put("userId", 10);
		others.put("completed", true);
		
		//Send Patch Request
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								auth().
								basic("admin", "password123").
								body(patchReqBody).
							when().
							    patch("/{todos}/{id}");
		response.prettyPrint();
		
		//Assertion
		Todos actualData = response.as(Todos.class);
		System.out.println(actualData);
		
		assertEquals(patchReqBody.get("title"), actualData.getTitle());
		assertEquals(others.get("userId"), actualData.getUserId());
		assertEquals(others.get("completed"), actualData.getCompleted());
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
