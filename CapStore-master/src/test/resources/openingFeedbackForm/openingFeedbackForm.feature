Feature: Opening Feedback page 

	Scenario: Opening Feedback form 
		Given Product page
		When 'Write a review' button is clicked
		Then open feedback form
		