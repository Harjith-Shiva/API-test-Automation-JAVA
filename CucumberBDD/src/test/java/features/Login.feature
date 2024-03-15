
Feature: Validating the Google Place APIs

  
  Scenario Outline: verify of the place is being successfully added using the AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got success with status code 200   
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_ID created maps to "<name>" using "getPlaceAPI"
    
    
Examples:
|name    |language |address |
|home one| English |One World Alliance |
|home two| Tamil   |Poongavanam        |


@deletePlaceAPI
Scenario: Verify if Delete Place functionality is working

Given deletePalce Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
