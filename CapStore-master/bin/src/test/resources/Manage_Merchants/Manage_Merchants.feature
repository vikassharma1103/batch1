Feature: Managing Merchants by Admin
Admin can add/remove and invite Merchants


Scenario:Admin adds the Merchant
Given Merchant Details
When Add Button is clicked
Then Add the Merchant

Scenario:Admin removes the Merchant
Given Merchants list 
When Current Merchant is checked(selected) 
And Remove Button is clicked
Then Remove the Merchant/Merchants

Scenario:Admin invites a Merchant
Given Merchant Details
When Invite Merchant Button is clicked
Then Send Email to Merchant

