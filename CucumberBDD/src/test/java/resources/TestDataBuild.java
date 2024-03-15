package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.GoogleMapsBody;
import POJO.Location;

public class TestDataBuild {

	
	public GoogleMapsBody addPlacePayload(String name, String language,String address)
	{
		GoogleMapsBody body = new GoogleMapsBody();
        body.setAccuracy(50);
        body.setAddress(address);
        body.setLanguage(language);
        body.setPhone_number("123456789");
        body.setWebsite("https://rahulshettyacademy.com");
        body.setName(name);
        List<String> types = new ArrayList<String>();
        types.add("shoe park");
        types.add("vanam");
        body.setTypes(types);
        
     // passing values to a sub-JSON class
        Location location =  new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        body.setLocation(location);
        
        return body;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";
	}
}
