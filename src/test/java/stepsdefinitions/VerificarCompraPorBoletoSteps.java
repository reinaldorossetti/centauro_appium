package stepsdefinitions;

import centauro.suport.DriverFactory;
import centauro.screen.HomeScreen;
import centauro.screen.LoginScreen;
import centauro.screen.OrderScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * A responsabilidade do step definition é de fazer a chamada das funções e realizar o Assert.
 */

public class VerificarCompraPorBoletoSteps {

	// Inicializa as Classes
	HomeScreen homeScreen;
	OrderScreen orderScreen;
	LoginScreen loginscreen;

	// Instancia das Classes
	public VerificarCompraPorBoletoSteps() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		loginscreen = new LoginScreen(DriverFactory.getInstance());
		orderScreen = new OrderScreen(DriverFactory.getInstance());
	}

	@Given("I have an order")
	public void i_have_an_order() {
		homeScreen.goToLoginScreen();
		loginscreen.loginDefault();
	}
	
	@When("I check my last order")
	public void i_check_my_last_order() {
		homeScreen.goToLastOrder();
	}
	
	@Then("I shall verify the billet is right")
	public void i_shall_verify_the_billet_is_right() {
		orderScreen.verifyBilletCodeAndTotal();
	}
	
}
