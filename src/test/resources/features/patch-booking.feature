@patch-booking @regression
Feature: Patch booking in Restful Booker

  Scenario: Successfully updating a booking
    Given I have patch booking payload
    When I send a "PATCH_BOOKING" request using "PATCH" method
    Then I should receive a response with 200 status code