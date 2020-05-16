package centauro.screen;

import java.util.List;

import centauro.model.Product;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SearchResultScreen extends BaseScreen {

	public SearchResultScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='br.com.sbf.centauro:id/tvSuggestionTerm']")
	private MobileElement result;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='br.com.sbf.centauro:id/tvSuggestionTerm']")
	private List<MobileElement> listResults;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_purchase")
	private MobileElement btnComprar;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/bt_added_cart_feedback")
	private MobileElement btnMoveToCart;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/product_name")
	private MobileElement productName;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/product_name")
	private List<MobileElement> listProductName;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/tab_itemspan")
	private MobileElement btnLista;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/action_search")
	private MobileElement searchIcon;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/etSearchInputTerm")
	private MobileElement inputSearch;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tv_size_item")
	List<MobileElement> listTamSize;

	@AndroidFindBy(id = "br.com.sbf.centauro:id/tv_size_single")
	private MobileElement sizeSingle;

	public CartScreen addItemToCart() {
		String product_name = returnFromJsonData("product_name");
		System.out.println("Produto encontrando: " + product_name);
		selectOptionFromList(product_name.split(" ")[0], listResults);
		waitUntilElementIsDisplayed(productName);
		selectRandomFromList(listProductName);
		moveToBottom();
		verifyElementIsDisplayed(btnComprar);
		Boolean tam_size_result = selectFirstFromList(listTamSize);
		if (!tam_size_result){
			clickOn(sizeSingle);
		}
		clickOn(btnComprar);
		clickOn(btnMoveToCart);
		return new CartScreen(driver);
	}

	public SearchResultScreen searchItem() {
		clickOn(searchIcon);
		sendValue(inputSearch, returnFromJsonData("product_name"));
		pressEnterKeyboard();
		pressEnterKeyboard();
		return new SearchResultScreen(driver);
	}
}
