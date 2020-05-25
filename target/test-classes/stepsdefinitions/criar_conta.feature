Feature: Create account

  @account
  Scenario: create account
    Given I want to create an account
    When I fill the new account form
    Then I shall be logged