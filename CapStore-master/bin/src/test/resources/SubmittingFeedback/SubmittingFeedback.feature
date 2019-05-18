Feature: Submitting Feedback form

	Scenario: Submitting Feedback 
		Given Feedback form
		When 'Submit' button is clicked
		Then store feedback in database
		And Update ratings
		
		