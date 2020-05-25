package centauro.screen;

import java.util.List;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.runners.Parameterized;

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


	public LoginScreen goToLoginScreen() {
		clickOn(btnMenu);
		clickOn(btnLogin);
		return new LoginScreen(driver);
	}

	public void verifyEmail(String string) {
		clickOn(btnMenu);
		Assert.assertEquals(labelEmail.getText(), string);
	}

	public OrderScreen goToLastOrder() {
		clickOn(btnMenu);
		clickOn(btnMeusPedidos);
		clickOn(elemOrder);
		isElementPresent(classButton);
		return new OrderScreen(driver);
	}

}
