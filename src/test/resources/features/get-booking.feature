@get-booking
Feature: Retrieve booking ids from Restful Booker

  Scenario: Successfully retrieving a booking
    Given I set the base URL to get booking
    When I send a "GET_BOOKING" request using "GET" method
    Then I should receive a response with 200 status code

