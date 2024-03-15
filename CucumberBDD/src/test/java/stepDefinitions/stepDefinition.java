package stepDefinitions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import POJO.*;


@RunWith(Cucumber.class)
public class stepDefinition extends Utils {
	// Declaration of Specification Variables Globally
    RequestSpecification res;
    static ResponseSpecification responseSpec;
    Response response;
    static String place_ID;
    TestDataBuild data = new TestDataBuild();
    
	
    
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {
        
    	
        
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    	
    	
    }
	
	
	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource,String httpMethod) {
		
		APIResources resourceOfTheAPI = APIResources.valueOf(resource);
		
		if(httpMethod.equalsIgnoreCase("POST"))
			response = res.when().post(resourceOfTheAPI.getResource())
                .then().spec(responseSpec).extract().response();
		else if(httpMethod.equalsIgnoreCase("GET"))
			
			response = res.when().get(resourceOfTheAPI.getResource())
            .then().spec(responseSpec).extract().response();
			
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expectedvalue) {
        
        assertEquals(getJsonPath(response,key),Expectedvalue);
	}



	@Then("verify place_ID created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourceName) throws IOException {
	    
		
		place_ID = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification())
                .queryParam("place_id",place_ID);
		
		user_calls_with_post_http_request(resourceName,"GET");
		
		String name = getJsonPath(response,"name");
		assertEquals(expectedName,name);
		
		
    	
		
	}

	
	
	@Given("deletePalce Payload")
	public void delete_palce_payload() throws IOException {
	    
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_ID));
	}


}