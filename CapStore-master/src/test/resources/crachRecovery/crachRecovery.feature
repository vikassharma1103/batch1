Feature: Crach Recovery

 Background: Given User navigates to the cart page

   Scenario: User wants to see all the products in the cart
	Then Show all the products in the cart

   Scenario: User wants to remove the product from the cart
	When user removes the product from the cart
	Then cart is updated

   Scenario: User wants to purchase the product from the cart
	When product from the cart is ordered
	Then cart is updated