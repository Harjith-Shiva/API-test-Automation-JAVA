package ECommerceAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO.CreateOrder;
import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.Orders;
public class EcommereceAPItests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("harji@gmail.com");
		loginRequest.setUserPassword("Hello@123");
		
		RequestSpecification reqLogin = given().relaxedHTTPSValidation().spec(request).body(loginRequest);
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		String token = loginResponse.getToken();
		String UserId = loginResponse.getUserId();
		
		
		
		
		// ------------------------------------------Adding the product----------------------------------------------------------------------------------------------------------
		RequestSpecification addProductRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", token).build();
		
		RequestSpecification requestAddProduct = given().spec(addProductRequest).param("productName", "karnan kavasam")	
		.param("productAddedBy", UserId)
		.param("productCategory", "Armour")
		.param("productSubCategory", "shirts")
		.param("productPrice", "250000")
		.param("productDescription", "Suryaputhran")
		.param("productFor", "men")
		.multiPart("productImage", new File("C:\\Users\\Harji\\OneDrive\\Pictures\\karnan.jpg"));
		
		
		
		String addProductResponse = requestAddProduct.when().post("/api/ecom/product/add-product").then()
		.log().all().extract().response().asString();
		
		JsonPath js= new JsonPath(addProductResponse);
		String productId= js.get("productId");
		
		
		
		//----------------------------------------Creating the order-------------------------------------------------------------------------------------------------------------
		RequestSpecification createOrderBaseRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", token).build();
		
		
		Orders order1 = new Orders(); // setting values for Orders using POJO class
		order1.setCountry("India");
		order1.setProductOrderedId(productId);
		CreateOrder orders = new CreateOrder();
		
		List listOfOrders = new ArrayList<Orders>(); // list of orders in a list
		listOfOrders.add(order1);
		orders.setOrders(listOfOrders);
		
		
		
		RequestSpecification createOrderRequest = given().log().all().spec(createOrderBaseRequest).body(order1);
		
		String createOrderResponse = createOrderRequest.when().post("/api/ecom/order/create-order").then().log().all()
		.extract().response().asString();
		
		System.out.println(createOrderResponse);
		
		
		//--------------------------------------------deleting the order we have created-------------------------------------------------------------------------------------------
		RequestSpecification deleteOrderBaseRequest = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", token).build();
		
		
		RequestSpecification deleteOrderRequest = given().log().all().spec(deleteOrderBaseRequest).pathParam("productId", productId);
		String deleteOrderResponse = deleteOrderRequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
		
		JsonPath js1= new JsonPath(deleteOrderResponse);
	    Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
		
		
		
}
}
