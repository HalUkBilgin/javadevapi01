package testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummyRestApi {
	
	public HashMap<String, Object> expectedData = new HashMap<>();
	
	public HashMap<String, Object> setUpData(){

		expectedData.put("statusCode", 200);
		expectedData.put("fifthEmployee", "Airi Satou");
		expectedData.put("numOfEmployee", 24);
		expectedData.put("secondSalary", "170750");
		
		List<String> ageList = new ArrayList<>();
		ageList.add("40");
		ageList.add("21");
		ageList.add("19");
		
		expectedData.put("ages", ageList);
		
		HashMap<Object, Object> innerMap = new HashMap<>();
		innerMap.put("id", "11");
		innerMap.put("employee_name", "Jena Gaines");
		innerMap.put("employee_salary", "90560");
		innerMap.put("employee_age", "30");
		innerMap.put("profile_image", "");
		
		expectedData.put("eleventhEmployee", innerMap);
		
		return expectedData;
	}

}
