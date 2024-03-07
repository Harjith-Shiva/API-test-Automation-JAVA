package files;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {

	
	@Test
	public void sumOfCourses()
	{
		JsonPath js = new JsonPath(payload.CoursePrice());
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		int count = js.getInt("courses.size()");
		int actualTotalAmount = 0;
		for (int i =0; i < count; i++)
		{
			int copies = js.getInt("courses["+i+"].copies");
			int price = js.getInt("courses["+i+"].price");
			actualTotalAmount += copies * price;
			System.out.println("The actual amount is: "+actualTotalAmount);
			 
		}
	}
	}

