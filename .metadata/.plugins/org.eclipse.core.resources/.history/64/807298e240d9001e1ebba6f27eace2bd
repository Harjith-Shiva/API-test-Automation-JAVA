import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
RestAssured.baseURI = "https://rahulshettyacademy.com";
String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body(payload.AddPlace())
.when().post("maps/api/place/add/json")
.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
.header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
System.out.println(response);
JsonPath js = new JsonPath(response);
String placeID = js.getString("place_id");



String newAddress = "Bhelpur, 6th cross";
given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
.body("{\r\n"
		+ "\"place_id\":\""+placeID+"\",\r\n"
		+ "\"address\":\""+newAddress+"\",\r\n"
		+ "\"key\":\"qaclick123\"\r\n"
		+ "}\r\n"
		+ "")
.when().put("maps/api/place/update/json")
.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));


given().queryParam("key", "qaclick123").queryParam("place_id",placeID)
.when().get("maps/api/place/get/json")
.then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress));

Assert.assertEquals("Bhelpur, 6th cross", newAddress);


}
}
