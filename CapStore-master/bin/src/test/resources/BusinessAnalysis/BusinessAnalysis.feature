Feature: Business Analysis for given time period

Scenario: Ask for business analysis details
	Given Admin page logged in
	When clicked on Generate Business Analysis tab
	Then open form for asking time period

Scenario: Get Business Analysis Details
	Given fromDate and toDate mentioned
	When Generate Analysis button clicked
	Then show table for analysis done