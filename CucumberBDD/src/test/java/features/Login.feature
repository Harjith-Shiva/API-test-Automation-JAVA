
Feature: Validating the Google Place APIs

  
  Scenario: verify of the place is being successfully added using the AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceAPI" with POST http request
    Then the API call got success with status code 200   
    And "status" in response body is "OK"
    And "scope" in response body is "APP"