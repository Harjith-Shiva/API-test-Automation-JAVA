package Tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
public class oAuthDeSerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String[] courseTitles = {"selenium webdriver","cypress","protractor"};	
		
		
		
	String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	.formParam("grant_type", "client_credentials")
	.formParam("scope", "trust")
	.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
	.then().log().all().extract().asString();
	
	
	JsonPath js = new JsonPath(response);
	String accessToken = js.getString("access_token");
	
	
    GetCourse gc = given().queryParam("access_token", accessToken)
	.when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails?access_token=CCbqnjPs89EnD9qKkb4TMg==")
	.as(GetCourse.class);
    
   // print the course price for the "SoapUI webservices testing"
   List<Api> apiCourses = gc.getCourses().getApi();
   for(int i =0; i<apiCourses.size();i++)
   {
	   if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI webservices testing"))
		   System.out.println(apiCourses.get(i).getPrice());
   }
	
	
   
   // chck all the course titles for webautomation elements
   
   ArrayList<String> actualCourseTitles = new ArrayList<String>();
   List<WebAutomation> webAutomationCourses = gc.getCourses().getWebAutomation();
   for(int i =0; i<webAutomationCourses.size();i++)
   {
	   actualCourseTitles.add(webAutomationCourses.get(i).getCourseTitle());
   }
   
   List<String> expectedCourseTitles = Arrays.asList(courseTitles);
  Assert.assertTrue(actualCourseTitles.equals(expectedCourseTitles));
   
	}

}