package stepsdefinitions;

import centauro.model.Customer;
import centauro.screen.DriverFactory;
import centauro.screen.HomeScreen;
import centauro.screen.OrderScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VerificarCompraPorBoletoSteps {
	
	HomeScreen homeScreen;
	OrderScreen orderScreen;
	Customer customer;
	
	public VerificarCompraPorBoletoSteps() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		customer = DriverFactory.getCustomerInstance();
	}

	@Given("I have an order")
	public void i_have_an_order() {
		homeScreen.goToLoginScreen().loginDefault();
	}
	
	@When("I check my last order")
	public void i_check_my_last_order() {
		orderScreen = homeScreen.goToLastOrder();
	}
	
	@Then("I shall verify the billet is right")
	public void i_shall_verify_the_billet_is_right() {
		orderScreen.verifyBilletCodeAndTotal();
	}
	
}
