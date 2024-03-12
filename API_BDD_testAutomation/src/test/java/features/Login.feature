
Feature: Application Login
  I want to use this template for my feature file

  
  Scenario: Home page default login
    Given user is on landing page
    When user login into application with username and password
    Then home page is populated    
    And cards are displayed