Feature: Upload Image
 Upload images of products and coupon

	Scenario: Upload Product Image
		Given Merchant loggedin page
		When Click on upload Image
		Then Image will be copied to server machine
	
	Scenario: Upload Product Image
		Given Admin loggedin page
		When Click on upload Image
		Then Image will be copied to server machine

	Scenario: Upload coupon Image
		Given Admin loggedin Page
		When Click on Upload Image 
		Then Image will be copied to server machine
		