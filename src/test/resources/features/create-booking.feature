@create-booking
Feature: Create booking in Restful Booker

  Scenario: Successfully creating a booking
    Given I have a valid booking payload with booking details "Sarker" "Rashid" "100" "true" "2025-04-14" "2025-04-15" "Coffee"
    When I send a "CREATE_BOOKING" request using "POST" method
    Then I should receive a response with 200 status code
    And the response should contain a booking id
