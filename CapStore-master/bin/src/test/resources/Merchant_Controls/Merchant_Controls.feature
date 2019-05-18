Feature:Merchant Main Page controls

   Scenario:Merchant Adding Category
   Given Merchant Main Page
   When Add Button is clicked
   Then redirect to AddCategory 
   

   Scenario:Merchant Removing Category
   Given Merchant Main Page
   When Remove Button is clicked
   Then Remove Category/Categories
 
   
   Scenario:Inventory Page
   Given Manage Inventory Page
   When Check box is checked
   And Edit Button is clicked
   Then Make all the fields editable
   And Click Done Button

   Scenario:Inventory Page
   Given Manage Inventory Page
   When Check box is checked
   And Delete Button is clicked
   Then Delete Entry
   And Click Done Button

   Scenario:Performance Page
   Given Performance page opens up
   

   Scenario:Orders Page
   Given Orders page opens up
   
