Feature:Validation of User
Validate details for a given user and then give him access to the portal

Scenario: sending verification mail to user
	Given Registration page
	And Valid user details
	When Register button is pressed
	Then Send an email to the users mail id

Scenario: Invalid User
	Given Registration Page
	When details are invalid
	Then Stay on registration page

 
