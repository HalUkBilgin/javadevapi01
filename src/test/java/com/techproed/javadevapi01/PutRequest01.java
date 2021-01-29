package com.techproed.javadevapi01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Todos;
import testbaseclasses.TestBaseJsonPlaceHolder;

public class PutRequest01 extends TestBaseJsonPlaceHolder {
	
	/*
	 	When
	 		I send PUT Requst to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "userId": 21,
										    "title": "Wash the dishes",
										    "completed": false
										   }
	   Then 
	   	   Status code is 200
	   	   And response body is like  {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false,
									    "id": 198
									  }
	 */
	
	/*
	 	PUT Request is used to update data. 
	 	PUT Request is used for fully update.
	 	
	 	PUT Request needs;
	 	1)PUT Method
	 	2)URL
	 	3)Data

	*/
	
	@Test
	public void put01() {
		
		//Set the URL
		spec.pathParams("todos", "todos", 
				        "id", 198);
		
		//Set the put data
		Todos dataToUpdate = new Todos(21, 0, "Wash the dishes", false);
		
		//Send the PUT Request
		Response response = given().
				                contentType(ContentType.JSON).
								spec(spec).
								auth().
								basic("admin", "password123").
								body(dataToUpdate).
						    when().
						        put("/{todos}/{id}");
		response.prettyPrint();
		
		
		//Assert
		Todos actualData = response.as(Todos.class);
		System.out.println(actualData);
		
		assertEquals(dataToUpdate.getUserId(), actualData.getUserId());
		assertEquals(dataToUpdate.getTitle(), actualData.getTitle());
		assertEquals(dataToUpdate.getCompleted(), actualData.getCompleted());
		
	}

}
