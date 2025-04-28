@update-booking @regression
Feature: Update booking in Restful Booker

  Scenario: Successfully updating a booking
    Given I have updated booking payload "Tanjina" "Rashid" "150" "false" "2025-04-29" "2025-04-30" "Coffee"
    When I send a "UPDATE_BOOKING" request using "PUT" method
    Then I should receive a response with 200 status code