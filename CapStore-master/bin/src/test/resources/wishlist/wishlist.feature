
Feature: Wishlist in CapStore
  Customer can add items to this wishlist and later on can see it and buy items from it

  Scenario: Adding Items to Wishlist 
    Given Open list of Product Page 
    When Clicked on add to wishlist for a particular product
    Then Add to Wishlist

  Scenario: View Items on Wishlist and buy from it 
    Given Open Wishlist Page 
    When Clicked on Add to cart for a particular product
    Then Add to Cart 
    
  Scenario: Removing Items from Wishlist 
    Given Open Wishlist Page 
    When Clicked on Remove from wishlist for a particular product
    Then Remove from Wishlist

    
    