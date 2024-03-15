package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefinition toCallMethods = new stepDefinition();
		
		if(stepDefinition.place_ID==null)
		{
		toCallMethods.add_place_payload_with("Harjith Shiva", "Tamil","Tamil Nadu");
		
		toCallMethods.user_calls_with_post_http_request("addPlaceAPI","POST");
		toCallMethods.verify_place_id_created_maps_to_using("Harjith Shiva", "getPlaceAPI");
		}
		}
	
}