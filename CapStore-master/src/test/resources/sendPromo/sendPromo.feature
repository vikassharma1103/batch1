Feature: Send promotional email

   Scenario: Send promotional email when new product added
	Given New products are added in product list
	Then Send promotional email to customer

   Scenario: Send promotional email when offer added
	Given New offers are added in product list
	Then Send promotional email to customer