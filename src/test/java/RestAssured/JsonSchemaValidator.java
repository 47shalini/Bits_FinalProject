package RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io. restassured. module.jsv. JsonSchemaValidator. matchesJsonSchemaInClasspath;

public class JsonSchemaValidator {

	@Test
	public void schemaValidator() {
		
		// Setting the base URI of the API we're going to test
		baseURI = "https://reqres.in/";
		
		// Sending a GET request to /api/users?page=2
		// Validating that the response body matches the JSON Schema defined in schema.json
		// Also verifying that the status code is 200 (OK)
		// Finally, logging the full response details to the console
		given().
		get("/api/users?page=2").
		then().
		assertThat().body(matchesJsonSchemaInClasspath("schema.json")).
		statusCode(200).
		log().all();
		
		/*
		 * Note:
		 * The file schema.json is used to define the expected structure of the response JSON.
		 */
	}
}
