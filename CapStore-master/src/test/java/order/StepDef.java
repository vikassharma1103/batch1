package order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDef {

	private WebDriver webdriver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\muasif\\Desktop\\chromedriver\\chromedriver.exe");
		webdriver = new ChromeDriver();
	}

	@Given("^Customer has checked in the cart$")
	public void customer_has_checked_in_the_cart() throws Throwable {
		webdriver.get("//cart url");
	}

	@When("^products are in sufficient quantity in the product list$")
	public void products_are_in_sufficient_quantity_in_the_product_list() throws Throwable {

	}

	@Then("^Order is placed$")
	public void order_is_placed() throws Throwable {

	}

	@Then("^Product list is updated\\.$")
	public void product_list_is_updated() throws Throwable {

	}

	@When("^Order is placed successfully$")
	public void order_is_placed_successfully() throws Throwable {

	}

	@Then("^Product list will be updated$")
	public void product_list_will_be_updated() throws Throwable {

	}

	@Then("^product will be delivered to the customer$")
	public void product_will_be_delivered_to_the_customer() throws Throwable {

	}

	@Then("^Admin updates the status of delivery$")
	public void admin_updates_the_status_of_delivery() throws Throwable {

	}

	@Then("^Customer can check the status of delivery$")
	public void customer_can_check_the_status_of_delivery() throws Throwable {

	}

}
