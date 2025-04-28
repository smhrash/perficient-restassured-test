@create-auth-token @regression
Feature: Auth Token Creation
  In order to securely access the PUT and DELETE /booking endpoints,
  As an admin,
  I want to create a new authentication token.

  Scenario: As an Admin I create a new auth token
    Given I have the necessary credentials for authentication
    When I send a "CREATE_TOKEN" request using "POST" method
    Then I should receive a response with 200 status code
    And the response should include the new auth token