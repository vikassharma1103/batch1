Feature: Refund Money
 Refunding money after verifying products

 
Background: Refund Money
 Given Returned Product
 
  Scenario: Refunding Money
    When Is verified by admin
    Then Initiate the refund process
    
  Scenario: Cant Refund Money
    When Is verified by admin and not permitted to initiate refund
    Then Cant Initiate the refund process
    

 