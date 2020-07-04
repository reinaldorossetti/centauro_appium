package centauro.screen;

import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


/**
 * A responsabilidade da Page é transformar os elementos da tela em objetos e realizar as ações.
 * As funções devem ter no Máximo 20 linhas, o que é generico deve estar na BaseScreen.
 */

public class CartScreen extends BaseScreen {

	public CartScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "br.com.sbf.centauro:id/btContinuarCompra")
	private MobileElement btnSelecionarEntrega;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_continuar_ou_pagar_vt")
	private MobileElement btnIrParaPagamento;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/rbBilletPaymentMethod")
	private MobileElement btnBoletoBancario;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/btFinalizarPagamento")
	private MobileElement btnConfirmarPagamento;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/btAcaoResumoPedido")
	private MobileElement btnVerBoletoBancario;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvValorTotalPedido")
	private MobileElement lblValorTotal;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_copiar_linha_digitavel")
	private MobileElement btnCopiarCode;

	@AndroidFindBy(xpath = "//android.webkit.WebView/android.webkit.WebView/android.widget.Button")
	private MobileElement lblImprimirBoleto;

	String source;
	String valorTotal;

	public void finishShopping() {
		clickOn(btnSelecionarEntrega);
		clickOn(btnIrParaPagamento);
		clickOn(btnBoletoBancario);
		clickOn(btnConfirmarPagamento);
	}
	
	public void verifyPayableCode() {
		waitUntilElementIsClickable(btnVerBoletoBancario);
		moveToBottom();
		swipeHalfScreen();
		valorTotal = lblValorTotal.getText();
		clickOn(btnVerBoletoBancario);
		clickIf(btnCopiarCode);
	}

	public void checkBoleto(){
		waitUntilElementIsDisplayed(lblImprimirBoleto);
		source = driver.getPageSource();
	}

	public Boolean containsvalorTotal(){
		return source.contains(valorTotal) ? true : false;
	}

	public Boolean containsCPF(){
		return source.contains(returnFromJsonData("cpf")) ?  true : false;
	}

}
