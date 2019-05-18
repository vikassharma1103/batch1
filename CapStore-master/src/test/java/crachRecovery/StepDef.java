package crachRecovery;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;
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

	@Then("^Show all the products in the cart$")
	public void show_all_the_products_in_the_cart() throws Throwable {
		webdriver.get("//cart page url");
		Thread.sleep(2000);
	}

	@When("^user removes the product from the cart$")
	public void user_removes_the_product_from_the_cart(String proName) throws Throwable {
		webdriver.findElement(By.name(proName));
		Thread.sleep(2000);
	}

	@Then("^cart is updated$")
	public void cart_is_updated() throws Throwable {
		
		Thread.sleep(2000);
	}

	@When("^product from the cart is ordered$")
	public void product_from_the_cart_is_ordered() throws Throwable {
		Thread.sleep(2000);
	}
}
