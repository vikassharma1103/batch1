Feature: Email Service
background: open login page
	Given mail page of user
	
	Scenario: changing the verification status of user
		When verification link is clicked
		Then  change isVerified field to 'true'