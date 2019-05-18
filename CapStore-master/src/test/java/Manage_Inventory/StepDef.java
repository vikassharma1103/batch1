package Manage_Inventory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.capstore.model.Inventory;
import com.capstore.model.Product;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {

	private WebDriver webdriver;
	private WebElement element;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\papurohi\\Desktop\\chromedriver.exe");
		webdriver = new ChromeDriver();

	}

	@Given("^Details of product$")
	public void details_of_product() throws Throwable {

	}

	@When("^Edit Button is clicked$")
	public void edit_Button_is_clicked() throws Throwable {
	}

	@Then("^Make all the fields editable$")
	public void make_all_the_fields_editable() throws Throwable {
	}

	@Then("^Click Save Button$")
	public void click_Save_Button() throws Throwable {
	}

	@When("^Delete Button is clicked$")
	public void delete_Button_is_clicked() throws Throwable {
	}

	@Then("^Delete Entry$")
	public void delete_Entry() throws Throwable {
	}

}
