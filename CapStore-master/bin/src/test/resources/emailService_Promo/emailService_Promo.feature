Feature:Sending Promos & Products through Email

Scenario:Merchant sends Promos & Products to Admin
Given Promo & Products Details
When Merchant clicks Send Button
Then Send mail to Admin


Scenario:Admin receives Mail from merchant and forwards to Customers
Given Received Promo  & new Products List mail from merchants
When Admin selects Promo & Products
And Admin clicks Send Button
Then Send mail to all Customers
