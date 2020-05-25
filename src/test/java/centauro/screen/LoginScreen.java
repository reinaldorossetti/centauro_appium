package centauro.screen;

import centauro.model.Customer;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginScreen extends BaseScreen {

	public LoginScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/btLoginGoToRegisterAccount")
	private MobileElement btnRegisterAccount;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/etLoginUsername")
	private MobileElement inputEmail;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/etLoginUserPassword")
	private MobileElement inputPassword;
	
	@AndroidFindBy(id = "br.com.sbf.centauro:id/btActionLoginUser")
	private MobileElement btnLogin;
	
	public RegisterScreen goToRegisterScreen() {
		clickOn(btnRegisterAccount);
		return new RegisterScreen(driver);
	}
 
	public HomeScreen loginCustomer(Customer customer) {
		System.out.println("Email: " + customer.email + " - Password: " + customer.password);
		sendValue(inputEmail, customer.email);
		hideKeyboard();
		inputPassword.click();
		sendValue(inputPassword, customer.password);
		hideKeyboard();
		clickOn(btnLogin);
		return new HomeScreen(driver);
	}

	public HomeScreen loginDefault() {
		String email = returnFromJsonData("email");
		String password = returnFromJsonData("password");
		System.out.println("Email: " + email);
		sendValue(inputEmail, email);
		hideKeyboard();
		inputPassword.click();
		sendValue(inputPassword, password);
		hideKeyboard();
		clickOn(btnLogin);
		return new HomeScreen(driver);
	}
}
