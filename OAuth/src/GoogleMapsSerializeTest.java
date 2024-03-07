import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.GoogleMapsBody;
import POJO.Location;
public class GoogleMapsSerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI ="https://rahulshettyacademy.com";



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




		Response response = given().log().all().queryParam("key", "qaclick123")
		.body(body)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		
		String responseAsString = response.asString();
		System.out.println(responseAsString);
		
		
		
	}

}
