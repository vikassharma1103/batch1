Feature: Search bar
 Searching according to the user
 Scenario Outline: Search by different users 
   Given User details
   When <particularUser> is Logged in
   Then <search> accordingly
   Examples:
   |particularUser|search|
   |Merchant      |List of Inventory      |
   |Admin         |List of Customers,Merchants and Inventory     |
   |User          |List of Products, Brands     |
   
