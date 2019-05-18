Feature: Transaction for a placed order

   Scenario: Getting Transaction Details
	Given Order is placed 
	When Navigated to payment page
	Then Get transaction details

   Scenario: Transaction Processing
	Given Payment details
	When valid payment details
	Then confirm transaction
	And get money from user's account
	And update CapStore revenue