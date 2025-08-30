package RestAssured;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDeleteRequest {
	@Test
	public void PutRequest() {
		// PUT → Replaces existing data completely.

		// Create a new JSON object
		JSONObject request = new JSONObject();

		// Add data to the JSON body
		request.put("name", "Raghav");
		request.put("job", "test");

		// Print the JSON as string
		System.out.println(request.toJSONString());

		// Set the base URI for the request
		baseURI = "https://reqres.in/api";

		// Prepare and send the PUT request
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON)
			.body(request.toJSONString()).
		when().
			put("/users/2").
		then().
			statusCode(200).
			log().all();
	}

	@Test
	public void PatchRequest() {
		// PATCH → Updates only the specified fields.

		JSONObject request = new JSONObject();
		request.put("name", "Raghav");
		request.put("job", "test");
		System.out.println(request.toJSONString());

		baseURI = "https://reqres.in/api";
		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(request.toJSONString()).when().patch("/users/2").then().statusCode(200).log().all();
	}

	@Test
	public void DeleteRequest() {
		// DELETE → Removes a resource

		baseURI = "https://reqres.in/api";
		when().delete("/users/2").then().statusCode(204).log().all();
	}
}
