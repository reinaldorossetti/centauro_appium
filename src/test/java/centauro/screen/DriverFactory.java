package centauro.screen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import centauro.model.Customer;
import centauro.model.Product;
import centauro.properties.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	final static String URL_STRING = "http://127.0.0.1:4723/wd/hub";
	private static AppiumDriver<MobileElement> driver;
	private static Customer customer;
	private static Product product;
	public static AppiumDriver<MobileElement> getInstance() {
		
		if (getDriver() == null) {
			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, LoadProperties.getConfig("device"));
			caps.setCapability(MobileCapabilityType.UDID, LoadProperties.getConfig("udid"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Integer.parseInt(LoadProperties.getConfig("timeout")));
			caps.setCapability("autoGrantPermissions", true);
			caps.setCapability(MobileCapabilityType.APP, new File("src/test/resources/apps/centauro.apk").getAbsolutePath());

			URL url = null;
			try {
				url = new URL(URL_STRING);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			driver = new AndroidDriver<MobileElement>(url, caps);	
		}
		return getDriver();
	}
	
	public static Customer getCustomerInstance() {
		if(getCustomer() == null) {
			customer = new Customer();

			writeToFile();
		    
		}
		return getCustomer();
	}

	public static Product getProductInstance() {
		if(getProduct() == null) {
			product = new Product();

			writeToFile();

		}
		return getProduct();
	}

	private static void writeToFile() {
		FileWriter writer = null;
		try {
			writer = new FileWriter("output_customer.txt", true);
		    writer.write(getCustomer().cpf + " - " + getCustomer().email + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
		    try{
		        if(writer != null) writer.close();
		    } catch(Exception ex){
		    }
		}
	}
	
	private static Customer getCustomer() {
		return customer;
	}

	private static Product getProduct() {
		return product;
	}
	
	private static AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	public static void setDriver(AppiumDriver<MobileElement> driver) {
		DriverFactory.driver = driver;
	}
	
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();			
			DriverFactory.driver = null;
		}
	}
}
