package validation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {
	
	
	private WebDriver webdriver;
	private WebElement webelement, element;
	private String message;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mbommath\\Downloads\\chromedriver_win32\\chromedriver.exe");
		webdriver = new ChromeDriver();
	}
	
	
	@Given("^Registration page$")
	public void registration_page() throws Throwable {
	    
		webdriver.get("");
		
	}

	@Given("^Valid user details$")
	public void valid_user_details() throws Throwable {
	    
	}

	@When("^Register button is pressed$")
	public void register_button_is_pressed() throws Throwable {
		element = webdriver.findElement(By.xpath("//*[@id=\"btnPayment\"]"));
		element.click();  
	}

	@Then("^Send an email to the users mail id$")
	public void send_an_email_to_the_users_mail_id() throws Throwable {
	    
	}

	@Given("^Registration Page$")
	public void registration_Page() throws Throwable {
	    
	}

	@When("^details are invalid$")
	public void details_are_invalid() throws Throwable {
	   
	}

	@Then("^Stay on registration page$")
	public void stay_on_registration_page() throws Throwable {
	  
	}

	
	
	
}
