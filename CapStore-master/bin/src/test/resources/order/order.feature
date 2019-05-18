Feature: placing, tracking and delivering of an order

   Scenario: Placing an order
  	Given Customer has checked in the cart 
	When products are in sufficient quantity in the product list
	Then Order is placed
	And Product list is updated.

   Scenario: Order tracking and delivery
 	When Order is placed successfully
	Then Product list will be updated
	And product will be delivered to the customer

   Scenario: checking and updating status of delivery
 	When Order is placed successfully
	Then Admin updates the status of delivery
	And Customer can check the status of delivery