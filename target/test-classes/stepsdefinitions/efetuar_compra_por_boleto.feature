Feature: Billet buying

  @buying
  Scenario: Buying with Billet
    Given I want to buy some item
    When I add the item to cart
    And I finish shopping
    Then I shall receive a valid billet