package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification requestSpec;
	public RequestSpecification requestSpecification( ) throws IOException
	{
		if(requestSpec==null)
		{
		PrintStream logs = new PrintStream(new FileOutputStream("logs.txt"));
		 RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
	                .addFilter(RequestLoggingFilter.logRequestTo(logs))
	                .addFilter(ResponseLoggingFilter.logResponseTo(logs)).build();

		 return requestSpec;
		}
		return requestSpec;
	}
	
	
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties property = new Properties();
		FileInputStream file = new FileInputStream("C:\\Users\\Harji\\eclipse-workspace-new\\CucumberBDD\\src\\test\\java\\resources\\global.properties");
		property.load(file);
		return property.getProperty(key);
	}
	
	public String getJsonPath(Response response, String resource)
	{
		String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(resource).toString();
	}
}
