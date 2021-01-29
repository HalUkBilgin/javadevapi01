package com.techproed.javadevapi01;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojos.Todos;
import testbaseclasses.TestBaseJsonPlaceHolder;

public class PatchRequest01 extends TestBaseJsonPlaceHolder {
	
	/*
	 	When
	 		I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 		with the PUT Request body like {
										    "title": "Tidy your room",
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
		
		//Set the patch data
		Todos dataToUpdate = new Todos();
		dataToUpdate.setTitle("Tidy your room");
		
		//Send the PATCH Request
		Response response = given().
				                contentType(ContentType.JSON).
								spec(spec).
								auth().
								basic("admin", "password123").
								body(dataToUpdate).
						    when().
						        patch("/{todos}/{id}");
		response.prettyPrint();
		
		
		//Assert
		Todos actualData = response.as(Todos.class);
		System.out.println(actualData);

		assertEquals(dataToUpdate.getTitle(), actualData.getTitle());
		
	}
}
