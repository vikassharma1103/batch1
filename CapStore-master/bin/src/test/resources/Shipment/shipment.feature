

Feature: To commit changes into shipment table


  Scenario: Placing a order with successful details 
    Given Shipment Page
    And AddressId
    And ProductId
    When clicks on submit button
    Then navigate to next page
 
