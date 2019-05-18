package SubmittingFeedback;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.capstore.model.Feedback;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {


	private WebDriver webdriver;
	private WebElement element;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\mbommath\\Downloads\\chromedriver.exe" );
		webdriver=new ChromeDriver();

	}

	@Given("^Feedback form$")
	public void feedback_form() throws Throwable {
		//Feedback feedback= new Feedback(1, 1, 1, 5, 5, "Good!", 1);
	}

	@When("^'Submit' button is clicked$")
	public void submit_button_is_clicked() throws Throwable {
	}

	@Then("^store feedback in database$")
	public void store_feedback_in_database() throws Throwable {
	}

	@Then("^Update ratings$")
	public void update_ratings() throws Throwable {
	}
}
