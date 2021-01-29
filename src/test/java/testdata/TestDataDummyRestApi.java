package testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummyRestApi {
	
	HashMap<String, Object> expectedData = new HashMap<>();
	
	public HashMap<String, Object> setUpData(){
		
		expectedData.put("statusCode", 200);
		expectedData.put("fifthEmpName", "Airi Satou");
		expectedData.put("numOfEmployees", 24);
		expectedData.put("secondLastSalary", "106450");
		
		List<String> ageList = new ArrayList<>();
		ageList.add("40");
		ageList.add("21");
		ageList.add("19");
		
		expectedData.put("ages", ageList);
		
		HashMap<String, Object> innerMap = new HashMap<>();
		innerMap.put("id", "11");
		innerMap.put("employee_name", "Jena Gaines");
		innerMap.put("employee_salary", "90560");
		innerMap.put("employee_age", "30");
		innerMap.put("profile_image", "");
		
		expectedData.put("eleventhEmployee", innerMap);
		
		return expectedData;
	}

}
