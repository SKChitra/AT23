package APIStepDefinition;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;



public class apistep {

	/**
	 * Post pet image, id. 
	 * Get the status code 200
	 * Post request
	 * @param dataTable
	 */
	@Given("user uploads image, petid")
	public void upload_Image(DataTable dataTable) {
		System.out.println("==========================================================");
		String Image;
		String PetId;
		String PetName;
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> ele : data) {
			Image = ele.get("image");
			PetId = ele.get("petid");
			PetName = ele.get("metadata");			
			String url = "https://petstore.swagger.io/v2/pet/" +PetId+"/uploadImage";
			System.out.println(url);			
			File file = new File(Image);
			Response response = RestAssured
					.given()
					.multiPart("file", file, "multipart/form-data")
					.post(url).thenReturn();
			System.out.println(response.asPrettyString());
			int statusCode = response.statusCode();
			if(statusCode ==200) {
				System.out.println("Status Code is Valid "+ statusCode + " ************************");
				}else {
				System.out.println("Status Code is invalid " +statusCode);
			}
		}
}
	
	/**
	 * Post a new pet id, name and status and get status code 200
	 * Post request
	 */
	@Then("user adds new pet to the store with petid, name and status")	
	public void addPet() {
		System.out.println("==========================================================");
		String requestPayload= "{\n"
				+ "  \"id\": 104,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 0,\n"
				+ "    \"name\": \"Labrador\"\n"
				+ "  },\n"
				+ "  \"name\": \"\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 2,\n"
				+ "      \"name\": \"Coco\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"available\"\n"
				+ "}";
		Response response= RestAssured.given()
				.baseUri("https://petstore.swagger.io")
				.contentType(ContentType.JSON)
				.body(requestPayload)
				.when()
				.post("/v2/pet")
				.then().extract().response();	
		JsonPath jsp = response.jsonPath();
		String status = jsp.get("status");
		String name = jsp.getString("tags.name");
		System.out.println(status +"; " + name);
		
	}
	
	/**
	 * Update a pet status and get the status code
	 * Put request  
	 */
	@Then("user updates petstatus")
	public void updatePet() {	
		System.out.println("==========================================================");
		String requestPayload= "{\n"
				+ "  \"id\": 104,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 0,\n"
				+ "    \"name\": \"Labrador\"\n"
				+ "  },\n"
				+ "  \"name\": \"\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 2,\n"
				+ "      \"name\": \"Coco\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"pending\"\n"
				+ "}";
		Response response =  RestAssured.given().baseUri("https://petstore.swagger.io/v2/pet/")
				.contentType(ContentType.JSON).body(requestPayload)
                .when().put().then()
               .extract().response();		
		int code = response.statusCode();
		JsonPath jsp = response.jsonPath();
		String status = jsp.get("status");
		int id = jsp.get("id");
		String name = jsp.getString("tags.name");
		System.out.println(code + "; "+name+"; "+ status + "; " +  id);
	}
	
	/**
	 * Get request
	 * List pets by status
	 */
	@When("user filters pets by status")
	public void getPetByStatus(DataTable dataTable) 
	{
		System.out.println("==========================================================");
		String status;
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> ele : data) 
		{
			status = ele.get("status");
		Response response =  RestAssured.given().when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=" +status)
         .then().extract().response();
		int responsecode = response.getStatusCode();
		System.out.println(responsecode );
		JsonPath jsp = response.jsonPath();
		List<String> idCount= jsp.getList("id");
		System.out.println("Number of " + status + ": " +idCount.size());		
	 }
}
	/**
	 * Delete Request
	 * Delete an existing pet and verify the response
	 */
	@Then("user enters petid to delete and validates the response code")
	public void deletePetById() {	
		System.out.println("==========================================================");
	Response response =  RestAssured.given().baseUri("https://petstore.swagger.io/v2/pet/9223372036854755000").contentType(ContentType.JSON)
                .when()
                .delete()
         .then().extract().response();        
	int statusCode = response.statusCode();
	System.out.println(statusCode); 


	}
	
	/**
	 * Get Request
	 * Find pet by Id 
	 * Get name and status of the pet
	 */
	@Then("user finds pet by id")
	public void findPetById() {
		System.out.println("==========================================================");
		Response resp = RestAssured.given().baseUri("https://petstore.swagger.io/v2/pet/9223372036854755000")
				.when().get().then().extract().response();
		System.out.println(resp.statusCode());
		JsonPath jsp = resp.jsonPath();
		String name = jsp.getString("name");
		String status = jsp.getString("status");
		System.out.println(name + " " +status);	
	}
	
	
}
