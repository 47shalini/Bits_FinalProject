package RestAssured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostRequest {

	// First test: Demonstrates how to convert a Java Map into a JSON object
	@Test
	public void mapToJson() {

		// Creating a HashMap and adding key-value pairs (name and job)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Raghav");
		map.put("job", "Teacher");

		// Printing the map to console (will show as a Java map)
		System.out.println(map);

		// Converting the map to a JSON object using JSONObject
		JSONObject request = new JSONObject(map);

		// Printing the JSON string representation of the request body
		System.out.println(request.toJSONString());
	}

	// Second test: Actually sends a POST request with a JSON body to the API
	@Test
	public void addDataInJson() {
		
		// Creating a new JSON object and adding data manually
		JSONObject request = new JSONObject();
		request.put("name", "Raghav");
		request.put("job", "test");
		
		// Printing the final JSON string that will be sent in the POST request
		System.out.println(request.toJSONString());

		// Setting the base URI of the API
		baseURI = "https://reqres.in";
		
		// Building and sending the POST request
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/api/users").
		then().
			statusCode(201).
			log().all();
	}

}
