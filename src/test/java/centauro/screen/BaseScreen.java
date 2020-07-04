package centauro.screen;

import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import centauro.model.Customer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import centauro.properties.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


/**
 * A responsabilidade da BaseScreen é modelar as funções globais para ser utilizadas nas Pages.
 * Assim diminuimos o codigo ao maximo possivel nas pages.
 * O correto é criar uma Interface BaseScreen e Criar Classes de Ações e Esperas.
 */

public class BaseScreen {
	
	protected AppiumDriver<MobileElement> driver;
	private WebDriverWait wait;
	private String[] dimensions;

	public BaseScreen(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		int timeoutValue = Integer.parseInt(LoadProperties.getConfig("timeout"));
		PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(timeoutValue/2)), this);
		this.wait = new WebDriverWait(driver, timeoutValue);
		this.dimensions = driver.manage().window().getSize().toString().split("\\D");
	}

	public void resetTime(){
		this.wait = new WebDriverWait(driver, Integer.parseInt(LoadProperties.getConfig("timeout")));
	}

	public Boolean findTextView(String text){
		try{
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[contains(@text, '" + text + "')]")));
			return element.isDisplayed();
		} catch (Exception ex){
			return false;
		}
	}

	protected void clickOn(MobileElement element) {
		resetTime();
		elementIsDisplayed(element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void clickIf(MobileElement element) {
		boolean result = elementIsDisplayed(element);
		if (result){
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
	}

	protected void sendValue(MobileElement element, CharSequence value) {
		elementIsDisplayed(element);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys((String) value);
	}

	protected boolean isElementPresent(WebElement element) {
		try{
		wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}catch(Exception e){
			return false;
		}
	}

	protected boolean elementIsDisplayed(MobileElement element) {
		try{
			return element.isDisplayed();
		} catch (Exception ex){
			System.out.println("Element not found swipe action!");
			swipeHalfScreen();
			return false;
		}
	}

	protected void swipeHalfScreen() {
		try{
			Dimension size = driver.manage().window().getSize();
			Point source = new Point(size.getWidth(), size.getHeight());
			int anchor = (int) (size.width / 2);
			int startPoint = (int) (size.height / 2);
			int endPoint = (int) (100);
			getTouchAction().press(PointOption.point(anchor, startPoint))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
					.moveTo(PointOption.point(anchor, endPoint)).release().perform();
		} catch (Exception ex){
			System.out.println("Swipe not working!");
		}

	}

	public TouchAction getTouchAction() {
		return new TouchAction(this.driver);
	}

	protected void hideKeyboard() {
		this.driver.hideKeyboard();
	}

	public void scrollToElementForAndroid(String value) {
		((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ value + "\").instance(0))");
	}

	protected void swipe(int initialPointX, int initialPointY, int endPointX, int endPointY, int seconds) {
		(new TouchAction(this.driver)).press(PointOption.point(initialPointX, initialPointY))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
				.moveTo(PointOption.point(endPointX, endPointY)).release().perform();
	}

	protected void selectOptionFromList(String value, List<MobileElement> elements) {
		for (MobileElement element : elements) {
			if (element.getText().toLowerCase().contains(value.toLowerCase())) {
				clickOn(element);
				break;
			}
		}
	}

	protected void selectRandomFromList(List<MobileElement> elements) {
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(elements.size());
		clickOn(elements.get(index));
	}

	protected Boolean selectFirstFromList(List<MobileElement> elements) {
			if (elements.size() > 0 ) {
				clickOn(elements.get(0));
				return true;
			} else {
				System.out.println("Nenhum Elemento Encontrado!");
				return false;
			}
		}

	public boolean waitUntilElementIsDisplayed(MobileElement element) {
		try {
		wait.until(ExpectedConditions.visibilityOf(element));
			return true;
	} catch (Exception e) {
			System.out.println("Element not found!");
		return false;
	}
	}

	public boolean waitUntilElementNotVisibility(MobileElement element) {
		try {
			wait.until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (Exception e) {
			System.out.println("Element not found!");
			return false;
		}
	}

	public boolean verifyElementIsDisplayed(MobileElement element) {
		try {
			this.wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			swipeHalfScreen();
			return false;
		}
	}
	
	public void waitUntilElementIsClickable(MobileElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception ex){
			System.out.println(ex.getMessage());
			swipeHalfScreen();
		}
	}

	protected void pressEnterKeyboard() {
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
	}

	protected void pressBackButton() {
		driver.navigate().back();
	}
	
	protected void moveToBottom() {
		swipe((int) getScreenWidth() / 2, (int) (getScreenHeight() * 0.8), (int) getScreenWidth() / 2,(int) (getScreenHeight() * 0.2), 2);
	}
	
	private int getScreenHeight() {
		return Integer.parseInt(dimensions[3]);
	}

	private int getScreenWidth() {
		return Integer.parseInt(dimensions[1]);
	}

	public String returnFromJsonData(String attribute) {
		JSONParser jsonParser = new JSONParser();
		String value = null;

		try {
			Object obj = jsonParser.parse(new FileReader(Customer.class.getClassLoader().getResource("data.json").getFile()));
			JSONObject jsonObject = (JSONObject)obj;
			value = (String) jsonObject.get(attribute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
