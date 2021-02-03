package utilities;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
	"utilities package" is used to store useful methods.
	If in your project, you need some mthods to use again and again, create "utilities"
	package and store the methods in it.
	*/

public class JsonUtil {
	
	//Create "De-Serialization" method by using ObjectMapper Class
	
	//1.Step:
	//Create an ObjectMapper Class object
	private static ObjectMapper mapper;
	
	static{
		mapper = new ObjectMapper();
	}
	
	//2.Step:
	//Create a method to convert Json Data to Java Object 
	public static <T> T convertJsonToJava(String json, Class<T> cls) {
		
		T javaObject = null;
		
		try {
			javaObject = mapper.readValue(json, cls);
		} catch (JsonParseException e) {
			System.out.println("Could not convert Json to Java object" + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Could not convert Json to Java object" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not convert Json to Java object" + e.getMessage());
		}

		return javaObject;
		
	}
	
	//Create a method for Serialization
	public static String convertJavaToJson(Object obj) {
		
		String jsonResult = null;
		
		try {
			jsonResult = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			System.out.println("Could not convert Java object to Json" + e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Could not convert Java object to Json" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Could not convert Java object to Json" + e.getMessage());
		}
		
		return jsonResult;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
