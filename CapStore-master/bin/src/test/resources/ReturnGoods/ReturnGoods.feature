
Feature: Return Goods
 Initiating and processing return goods

  Scenario: Return Goods
    Given Open My Orders page
    When The purchase date is less than 7 days ago from Current Date
    Then Initiate Return Goods
  
  Scenario: Cant Return Goods
    Given Open My Orders page
    When The purchase date is more than 7 days ago from Current Date
    Then Cant Return Goods