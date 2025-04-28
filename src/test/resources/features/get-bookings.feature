@get-bookings @regression
Feature: Retrieve booking ids from Restful Booker

  Scenario: Successfully retrieving booking ids
    Given I set the base URL to get booking
    When I send a "GET_BOOKINGS" request using "GET" method
    Then I should receive a response with 200 status code
    And the response should contain a list of booking ids
