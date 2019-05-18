Feature: Managing Cart
 Adding and removing products from Cart
 Scenario: Add items to the cart
  Given product page
  When add button is pressed
  Then product should be added to cart

 Scenario: Remove items from the cart
  Given cart page
  When delete button is pressed
  Then display alert box for confirmation
  When user clicks yes
  Then delete the product from cart list

 Scenario: Updating the cart
  Given cart page
  When quantity is selected from the list
  Then update the price of products
  And update the final cart amount before checking out
  
 Scenario: Checking minimum purchase
  Given total amount of purchased products
  When total amount is greater than minimum balance
  Then proceed to checkout