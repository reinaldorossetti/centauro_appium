package stepsdefinitions;

import centauro.screen.*;
import centauro.suport.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import org.junit.Assert;


/**
 * A responsabilidade do step definition é de fazer a chamada das funções e realizar o Assert.
 */

public class EfetuarCompraPorBoletoSteps {

	// Inicializa as Classes
	HomeScreen homeScreen;
	SearchResultScreen searchResultScreen;
	CartScreen cartScreen;
	LoginScreen loginscreen;

	// Instancia das Classes
	public EfetuarCompraPorBoletoSteps() {
		homeScreen = new HomeScreen(DriverFactory.getInstance());
		loginscreen = new LoginScreen(DriverFactory.getInstance());
		searchResultScreen = new SearchResultScreen(DriverFactory.getInstance());
	}
		
	@Given("que usuário efetue o login com sucesso")
	public void i_want_to_buy_some_item() {
		homeScreen.goToLoginScreen();
		loginscreen.loginDefault();
	}

	@When("adiciono um produto ao carrinho de compras")
	public void i_add_the_item_to_cart() {
		searchResultScreen.searchItem();
		searchResultScreen.addItemToCart();
	}

	@When("realizo a compra via boleto bancario")
	public void i_finish_shopping() {
		cartScreen.finishShopping();
	}

	@Then("I shall receive a valid billet")
	public void i_shall_receive_a_valid_billet() {
		cartScreen.verifyPayableCode();
	}

	@Então("deve visualizar o meu boleto para pagamento")
	public void deveVisualizarOMeuBoletoParaPagamento() {
		cartScreen.verifyPayableCode();
		cartScreen.checkBoleto();
	}

	@E("valido os dados do boleto")
	public void validoOsDadosDoBoleto() {
		Boolean result = cartScreen.containsvalorTotal();
		Boolean resultCPF = cartScreen.containsCPF();
		Assert.assertTrue(result);
		Assert.assertTrue(resultCPF);
	}
}
