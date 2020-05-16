Feature: Verify order Billet payment method

  @billet
  Scenario: paying method Billet
    Given I have an order
    When I check my last order
    Then I shall verify the billet is right