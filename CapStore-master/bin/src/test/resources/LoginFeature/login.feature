Feature: Login
Such as user login,merchant login and admin login

Scenario Outline: Successful login
Given emailid and password 
When Valid <emailId> and <password>
Then navigate to <page> 

Examples: 

|emailId|password|page|
|akhil123@gmail.com|A123|customerpage|
|sum123@yahoo.com|S123|merchantpage|
|abhi123@gmail.com|A123|adminpage|