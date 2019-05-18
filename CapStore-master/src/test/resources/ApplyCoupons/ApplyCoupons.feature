
Feature: Apply Coupons
 Customer can apply coupons and get Discount on final bill


Background: Refund Money
 Given Request for Verifying Coupons
 
 
Scenario: Applying Coupons
   
 When There exist a valid coupon code
    
Then Calculate the Discounted price
    
And Reflect on final bill

  

Scenario: Applying Coupons
   
 When There isnt a valid coupon code
   
 Then Decline the request 