package RestAssured;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FirstRestProgram {

	public static void main(String[] args) {
	
		// Making a simple GET request to a public API using RestAssured
		
		// Setting the base URI for the API calls
		RestAssured.baseURI="https://reqres.in/";
		
		// Sending a GET request to the users endpoint (page 2) and storing the response
		Response response = RestAssured.get("/api/users?page=2");
		
		// Printing the status code to check if the request was successful (should be 200)
		System.out.println(response.getStatusCode());
		
		// Printing how long the response took (in milliseconds)
		System.out.println(response.getTime());
		
		// Printing the response body as a plain string (not formatted) basically raw
		System.out.println(response.getBody().asString());
		
		// Printing the response body in a readable, pretty JSON format
		System.out.println(response.getBody().asPrettyString());
		
		// Printing the full status line of the response (e.g., HTTP/1.1 200 OK)
		System.out.println(response.getStatusLine());
		
		// Printing the value of the "content-type" header from the response
		System.out.println(response.getHeader("content-type"));
		
		// Validating that the status code is 200 — this confirms the API is working as expected
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
