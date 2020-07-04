package centauro.screen;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


/**
 * A responsabilidade da Page é transformar os elementos da tela em objetos e realizar as ações.
 * As funções devem ter no Máximo 20 linhas, o que é generico deve estar na BaseScreen.
 */

public class HomeScreen extends BaseScreen {

	public HomeScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='br.com.sbf.centauro:id/homeToolbar']/android.widget.ImageButton")
	private MobileElement btnMenu;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvHeaderNavUserEmail")
	private MobileElement btnLogin;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvHeaderNavUserEmail")
	private MobileElement labelEmail;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Meus Pedidos\")")
	private MobileElement btnMeusPedidos;

	@AndroidFindAll(value = {
			@AndroidBy(uiAutomator = "new UiSelector().text(\"Aguardando Pagamento\")"),
			@AndroidBy(id = "br.com.sbf.centauro:id/price"),
			@AndroidBy(id = "br.com.sbf.centauro:id/order_date"),
			@AndroidBy(id = "br.com.sbf.centauro:id/order_number"),
	})
	private MobileElement elemOrder;

	@AndroidFindBy(className = "android.widget.Button")
	private MobileElement classButton;


	public void goToLoginScreen() {
		clickOn(btnMenu);
		clickOn(btnLogin);
	}

	public String verifyEmail(String string) {
		clickOn(btnMenu);
		return labelEmail.getText();
	}

	public void goToLastOrder() {
		clickOn(btnMenu);
		clickOn(btnMeusPedidos);
		clickOn(elemOrder);
		isElementPresent(classButton);
	}

}
