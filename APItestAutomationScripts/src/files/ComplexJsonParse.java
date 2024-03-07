package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JsonPath js = new JsonPath(payload.CoursePrice());

		// no. of courses return by API using JSON Parse
int count =js.getInt("courses.size()");
System.out.println(count);

// printing the purchase amount

int purchaseAmount = js.getInt("dashboard.purchaseAmount");
System.out.println(purchaseAmount);
	
	// print title of all courses
String totalAmount = null;
for (int i =0; i < count; i++)
{
	
	System.out.print("Title: "+ js.get("courses["+i+"].title"));
	System.out.print(" --> Price:  "+ js.get("courses["+i+"].price"));
	System.out.println();
}

for (int i =0; i < count; i++)
{
	String title = js.get("courses["+i+"].title");
	if(title.equalsIgnoreCase("RPA"))
	{
		System.out.print(title + " --> Price:  "+ js.get("courses["+i+"].price"));
	break;
	}
	
}
	
	}

}
