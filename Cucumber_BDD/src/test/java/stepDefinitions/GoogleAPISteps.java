
package stepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import POJO.*;
public class GoogleAPISteps {
    // Declaration of Specification Variables Globally
    RequestSpecification res;
    ResponseSpecification responseSpec;
    Response response;
    @Given("Add Place Payload")
    public void add_place_payload() {
        GoogleMapsBody body = new GoogleMapsBody();
        body.setAccuracy(50);
        body.setAddress("29, side layout, cohen 09");
        body.setLanguage("English");
        body.setPhone_number("123456789");
        body.setWebsite("https://rahulshettyacademy.com");
        body.setName("Poongavanam");
        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("vanam");
        body.setTypes(types);


        // passing values to a sub-JSON class
        Location location =  new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        body.setLocation(location);


        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
                .build();

        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
                .build();

        res = given().spec(requestSpec)
                .body(body);
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
