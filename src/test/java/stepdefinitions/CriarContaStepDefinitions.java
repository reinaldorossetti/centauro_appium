package stepdefinitions;

import centauro.model.Customer;
import centauro.screen.DriverFactory;
import centauro.screen.HomeScreen;
import centauro.screen.RegisterScreen;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CriarContaStepDefinitions {
	
	HomeScreen homeScreen;
	RegisterScreen registerScreen;
	Customer customer;
	
	public CriarContaStepDefinitions() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		customer = DriverFactory.getCustomerInstance();
	}
	
	@Given("I want to create an account")
	public void i_want_to_create_an_account() {
		registerScreen = homeScreen.goToLoginScreen().goToRegisterScreen();
	}

	@When("I fill the new account form")
	public void i_fill_the_new_account_form() {
		registerScreen.fillForm(customer);
	}

	@Then("I shall be logged")
	public void i_shall_be_able_to_login() {
		homeScreen.verifyEmail(customer.email);
	}

}
