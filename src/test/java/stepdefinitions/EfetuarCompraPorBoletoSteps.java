package stepdefinitions;

import centauro.model.Customer;
import centauro.model.Product;
import centauro.screen.CartScreen;
import centauro.screen.DriverFactory;
import centauro.screen.HomeScreen;
import centauro.screen.SearchResultScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EfetuarCompraPorBoletoSteps {

	HomeScreen homeScreen;
	SearchResultScreen searchResultScreen;
	CartScreen cartScreen;
	Customer customer;
	Product product;
	
	public EfetuarCompraPorBoletoSteps() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		//product = DriverFactory.getProductInstance();
	}
		
	@Given("I want to buy some item")
	public void i_want_to_buy_some_item() {
		homeScreen.goToLoginScreen().loginDefault();
	}

	@When("I add the item to cart")
	public void i_add_the_item_to_cart() {
		searchResultScreen = new SearchResultScreen(DriverFactory.getInstance());
		cartScreen = searchResultScreen.searchItem().addItemToCart();
	}

	@When("I finish shopping")
	public void i_finish_shopping() {
		cartScreen.finishShopping();
	}

	@Then("I shall receive a valid billet")
	public void i_shall_receive_a_valid_billet() {
		cartScreen.verifyPayableCode();
	}
	
}
