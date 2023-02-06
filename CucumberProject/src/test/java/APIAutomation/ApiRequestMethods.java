package APIAutomation;

import java.io.File;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiRequestMethods {

	/**
	 * Upload images
	 * Add pet Id
	 * Add pet name
	 * Verify Response
	 */
	@Given("user uploads image, petid and metadata")
	public void upload_Image(DataTable dataTable)
	{
		String Image ="";
		String PetId="";
		String PetName="";
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		for(Map<String, String> ele : data) 
		{
			Image = ele.get("image");
			PetId = ele.get("petid");
			PetName = ele.get("metadata");
		}
		String url = "https://petstore.swagger.io/v2/pet/" +PetId+"/uploadImage";
		System.out.println(url);
		//upload images
		File file = new File(Image);
		Response response = RestAssured
				.given()
				.multiPart("file", file, "multipart/form-data").multiPart(PetName, file)
				.post(url).thenReturn();
		System.out.println(response.asPrettyString());
		int statusCode = response.statusCode();
		if(statusCode ==200) {
			System.out.println("Status Code is Valid "+ statusCode);
			System.out.println("==========================================================");
		}else {
			System.out.println("Status Code is invalid " +statusCode);
		}
	}
}
