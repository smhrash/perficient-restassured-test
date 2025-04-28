@healthcheck
Feature: A simple health check endpoint to confirm whether the API is up and running

  Scenario: Successfully checking whether the API is up and running
    Given I set the base URL to get booking
    When I send a "HEALTH_CHECK" request using "GET" method
    Then I should receive a response with 201 status code
