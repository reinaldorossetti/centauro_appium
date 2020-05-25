package centauro.screen;

import org.junit.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OrderScreen extends BaseScreen {

	public OrderScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_copiar_linha_digitavel")
	private MobileElement btnCopyLine;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvOrderPaymentValidBilletNumber")
	private MobileElement lblOrderPaymentValidBilletNumber;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvOrderPaymentValidBilletTotal")
	private MobileElement lblTotalValue;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tvOrderPaymentValidBilletExpireDate")
	private MobileElement lblExpireDate;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/btOrderPaymentValidBilletViewer")
	private MobileElement btnBilletViewer;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_copiar_linha_digitavel")
	private MobileElement btnCopiarCode;

	@AndroidFindBy(xpath = "//android.webkit.WebView/android.webkit.WebView/android.widget.Button")
	private MobileElement lblImprimirBoleto;
	
	public void verifyBilletCodeAndTotal() {
		if(!verifyElementIsDisplayed(lblOrderPaymentValidBilletNumber)) moveToBottom(); // This is due to an unpleasant bug on rendering the screen on random arrange
		String expireDate = lblExpireDate.getText();
		System.out.println(expireDate);
		String totalValue = lblTotalValue.getText();
		System.out.println(totalValue);
		swipeHalfScreen();
		clickOn(btnBilletViewer);
		clickIf(btnCopiarCode);
		waitUntilElementIsDisplayed(lblImprimirBoleto);
		String source = driver.getPageSource();

		Boolean resultTotalValue = source.contains(totalValue) ?  true : false;
		Boolean resultbilletCode = source.contains(expireDate) ?  true : false;
		Boolean resultCPF = source.contains(returnFromJsonData("cpf")) ?  true : false;
		Boolean resultAvalista = source.contains(returnFromJsonData("Sacador_Avalista")) ?  true : false;

		Assert.assertTrue(resultTotalValue);
		Assert.assertTrue(resultbilletCode);
		Assert.assertTrue(resultCPF);
		Assert.assertTrue(resultAvalista);
	}
}
