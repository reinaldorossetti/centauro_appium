package centauro.screen;

import java.util.List;

import centauro.model.Product;
import org.junit.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomeScreen extends BaseScreen {

	public HomeScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	// refinei o xpath dele, precisava melhorar
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='br.com.sbf.centauro:id/homeToolbar']/android.widget.ImageButton")
	private MobileElement btnMenu;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvHeaderNavUserEmail")
	private MobileElement btnLogin;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvHeaderNavUserEmail")
	private MobileElement labelEmail;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Meus Pedidos\")")
	private MobileElement btnMeusPedidos;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Aguardando Pagamento\")")
	private List<MobileElement> listOrders;

	// ficou bem feio esse xpath
	@AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout")
	private MobileElement lastItem;
	
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
		waitUntilElementIsDisplayed(lastItem);
		clickOn(listOrders.get(0));
		return new OrderScreen(driver);
	}

}
