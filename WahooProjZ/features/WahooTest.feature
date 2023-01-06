@wahooFullTest
Feature: Wahoo Full Test

  Scenario Outline: To verify functionality of Wahoo webpage.
    Given I am in the home page <url> of Wahoo.
    When I click on show all products.
    And I randomly select products until there are two different products in the cart.
    And I remove an item from the cart, then click view cart.
    And I update the item quantity <qty>, click on update cart, then verify price change. Next I proceed to check out.
    And I fill out customer shipping information <email>, <firstName>, <lastName>, <address>, <city>, <country>, <state>, <zipCode>, <phoneNum>.
    And I fill out the payment method <creditNum>, <creditExpDate>, <creditCV>.
    And I place the order.
    Then I verify message <errorMessage> to verify functionality.

    Examples: 
      | url                         | qty | email              | firstName | lastName | address               | city    | country         | state      | zipCode | phoneNum     | creditNum          | creditExpDate | creditCV | errorMessage                                                                         |
      | "https://wahoofitness.com/" | "3" | "tester@gmail.com" | "Test"    | "Tester" | "3145 Rochambeau Ave" | "Bronx" | "United States" | "New York" | "10467" | "3479147182" | "4111111111111111" | "0824"        | "1111"   | "Your card was declined. Your request was in live mode, but used a known test card." |
