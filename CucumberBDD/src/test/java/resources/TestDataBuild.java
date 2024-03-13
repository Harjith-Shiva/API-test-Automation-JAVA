package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.GoogleMapsBody;
import POJO.Location;

public class TestDataBuild {

	
	public GoogleMapsBody addPlacePayload()
	{
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
        
        return body;
	}
	
	
}
