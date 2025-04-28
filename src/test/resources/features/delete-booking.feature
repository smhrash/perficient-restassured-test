@delete-booking @regression
Feature: Delete booking from Restful Booker

  Scenario: Successfully deleting a booking
    Given I set the base URL and auth token to delete booking
    When I send a "DELETE_BOOKING" request using "DELETE" method
    Then I should receive a response with 201 status code