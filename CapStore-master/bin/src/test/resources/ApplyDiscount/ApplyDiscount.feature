
Feature: Apply Discount
  
If discount is on products the discounted price will be calculated and reflected on invoice
 

 
 Scenario: Apply Discount
   
 Given Open a particular product
   
 When There is a discount on it 
   
 Then calculate the Discounted Price
  
  And Reflect on Invoice
