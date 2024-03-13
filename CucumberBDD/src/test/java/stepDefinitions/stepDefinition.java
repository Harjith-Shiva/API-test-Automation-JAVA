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
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

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
    ResponseSpecification responseSpec;
    Response response;
	
	
	
	
	@Given("Add Place Payload")
	public void add_place_payload() {

		
		TestDataBuild data = new TestDataBuild();
       
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        res = given().spec(requestSpecification())
                .body(data.addPlacePayload());
        
        
	}
	@When("user calls {string} with POST http request")
	public void user_calls_with_post_http_request(String string) {
		
		response = res.when().post("/maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expectedvalue) {
		
		String responseAsString = response.asString();
        JsonPath js = new JsonPath(responseAsString);
        assertEquals(js.get(key).toString(),Expectedvalue);
	}


}
