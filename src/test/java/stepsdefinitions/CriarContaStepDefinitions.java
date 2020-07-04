package stepsdefinitions;

import centauro.model.Customer;
import centauro.suport.DriverFactory;
import centauro.screen.HomeScreen;
import centauro.screen.LoginScreen;
import centauro.screen.RegisterScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

/**
 * A responsabilidade do step definition é de fazer a chamada das funções e realizar o Assert.
 */

public class CriarContaStepDefinitions {

	// Inicializa as Classes
	HomeScreen homeScreen;
	RegisterScreen registerScreen;
	Customer customer;
	LoginScreen loginscreen;

	// Instancia das Classes
	public CriarContaStepDefinitions() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		registerScreen = new RegisterScreen(DriverFactory.getInstance());
		loginscreen = new LoginScreen(DriverFactory.getInstance());
		customer = DriverFactory.getCustomerInstance();
	}
	
	@Given("que o cliente acesse a tela de cadastro de usuario")
	public void i_want_to_create_an_account() {
		homeScreen.goToLoginScreen();
		loginscreen.goToRegisterScreen();
	}

	@When("preencher os dados do formulario e realizo seu processamento")
	public void i_fill_the_new_account_form() {
		registerScreen.fillForm(customer);
	}

	@Then("devo realizar o login com sucesso")
	public void i_shall_be_able_to_login() {
		String labelEmail = homeScreen.verifyEmail(customer.email);
		Assert.assertEquals(labelEmail, customer.email);
	}

}
